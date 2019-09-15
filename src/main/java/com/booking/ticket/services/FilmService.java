/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.services;


import com.booking.ticket.entities.Booking;
import com.booking.ticket.entities.Films;
import com.booking.ticket.entities.Schedule;
import com.booking.ticket.enums.Enums;
import com.booking.ticket.exceptions.NoSuchElementInDBException;
import com.booking.ticket.logger.LogMessage;
import com.booking.ticket.repositories.BookingRepository;
import com.booking.ticket.repositories.FilmRepository;
import com.booking.ticket.repositories.ScheduleRepository;
import com.booking.ticket.wrapper.SortWrapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author sardorrokhillaev
 */

@Service
@Transactional(rollbackFor = Exception.class)
public class FilmService {
    
    @Autowired
    private FilmRepository filmRepository;
    
    @Autowired
    private ScheduleRepository scheduleRepository;
    
    public JsonObject getFilms(SortWrapper wrapper, LogMessage message) {
        if (wrapper.getFilterType().equals(Enums.FilterTypes.NAME.toString())){
            return  returnFilmsList(filmRepository.findFilmsByName(wrapper.getName()));
        }
        if (wrapper.getFilterType().equals(Enums.FilterTypes.GENRE.toString())){
            return returnFilmsList(filmRepository.findFilmsByGenre(wrapper.getGenre()));
        }
        if (wrapper.getFilterType().equals(Enums.FilterTypes.POPULARITY.toString())){
            return returnFilmsList(filmRepository.findFilmsByPopularity());
        }
        else {
            return returnFilmsList(filmRepository.findNewFilms());
        }
    }
    
    public JsonObject getSchedule(SortWrapper wrapper, LogMessage message) {
        Films film = filmRepository.findFilmsById(wrapper.getFilmId());
        checkFilmOnNull(film, message);
        Calendar c = Calendar.getInstance(); 
        c.setTime(wrapper.getShowTime()); 
        c.add(Calendar.DATE, 1);
        Date showTime2 = c.getTime();
        System.out.println(showTime2);
        List<Schedule> scheduleList = scheduleRepository.findScheduleByCityAndFilmIdAndShowTimeBetween(wrapper.getCity(),film,wrapper.getShowTime()
                ,showTime2);
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 0);
        final JsonArray scheduleJA = new JsonArray();
        scheduleList.forEach(p -> scheduleJA.add(p.toJson()));
        resJO.add("schedule", scheduleJA);
        return resJO;
    }
    
    public void checkFilmOnNull(Films film, LogMessage message) {
        if (film == null) {
            message.setCause("No such film in database");
            message.setErrorCode(Enums.Error.WRONG_INPUT_DATA.toString());
            throw new NoSuchElementInDBException(message);
        }
    }
    
    private JsonObject returnFilmsList(@NotNull List<Films> filmsList) {
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 0);
        final JsonArray filmsJA = new JsonArray();
        filmsList.forEach(p -> filmsJA.add(p.toJson()));
        resJO.add("films", filmsJA);
        return resJO;
    }
    
}

