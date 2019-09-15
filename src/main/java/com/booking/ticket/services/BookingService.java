/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.services;

import com.booking.ticket.entities.Booking;
import com.booking.ticket.entities.Films;
import com.booking.ticket.enums.Enums;
import com.booking.ticket.exceptions.NoSuchElementInDBException;
import com.booking.ticket.logger.LogMessage;
import com.booking.ticket.repositories.BookingRepository;
import com.booking.ticket.repositories.FilmRepository;
import com.booking.ticket.wrapper.SortWrapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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
public class BookingService {
    
    @Autowired
    private FilmService filmService;
    
    @Autowired
    private BookingRepository bookingRepository;
    
    @Autowired
    private FilmRepository filmRepository;
    
    public JsonObject createBooking(Booking booking, LogMessage message) {
        filmService.checkFilmOnNull(booking.getFilmId(), message);
        if (booking.getPhoneNumber().equals("") || booking.getShowDay() == null 
                || booking.getShowTime().equals("") || booking.getPlace() == null) {
            message.setCause("not all required fills are filled");
            message.setErrorCode(Enums.Error.WRONG_INPUT_DATA.toString());
            throw new NoSuchElementInDBException(message);
        }
        //checking is place is empty
        List<Booking> bookings = bookingRepository.findBookingByShowDayAndShowTimeAndPlaceAndHall(booking.getShowDay(),
                booking.getShowTime(),booking.getPlace(), booking.getHall());
        System.out.print("booking :" + bookings);
        if(!bookings.isEmpty()){
            message.setCause("place is not empty");
            message.setErrorCode(Enums.Error.WRONG_INPUT_DATA.toString());
            throw new NoSuchElementInDBException(message);
        }else{
            System.out.print(booking.getShowTime());
            JsonObject resJO = new JsonObject();
            booking.setPhoneNumber(booking.getPhoneNumber());
            booking.setEmail(booking.getEmail());
            booking.setShowDay(booking.getShowDay());
            booking.setFilmId(booking.getFilmId());
            booking.setShowTime(booking.getShowTime());
            booking.setPlace(booking.getPlace());
            booking.setHall(booking.getHall());
            bookingRepository.save(booking);
            resJO.addProperty("error", 0);
            resJO.add("booking", booking.toJson());
            return resJO;
        }
    }
    
    public JsonObject getBookings(SortWrapper wrapper, LogMessage message) {
        if (wrapper.getFilterType().equals(Enums.FilterTypes.NUMBER.toString())){
            return returnBooking(bookingRepository.findBookingById(wrapper.getNumber()));
        }
        if (wrapper.getFilterType().equals(Enums.FilterTypes.RANGE.toString())){
            return returnBookingList(bookingRepository.findBookingByShowDayBetween(wrapper.getDateFrom(), wrapper.getDateTo()));
        }
        if (wrapper.getFilterType().equals(Enums.FilterTypes.FILM.toString())){
            Films film = filmRepository.findFilmsById(wrapper.getFilmId());
            filmService.checkFilmOnNull(film, message);
            return returnBookingList(bookingRepository.findBookingByFilmId(film));
        }
        else {
            return returnBooking(bookingRepository.findBookingById(wrapper.getNumber()));
        }
    }
    
    private JsonObject returnBooking(Booking booking) {
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 0);
        resJO.add("booking", booking.toJson());
        return resJO;
    }
    
    private JsonObject returnBookingList(@NotNull List<Booking> bookingList) {
        final JsonObject resJO = new JsonObject();
        resJO.addProperty("error", 0);
        final JsonArray bookingJA = new JsonArray();
        bookingList.forEach(p -> bookingJA.add(p.toJson()));
        resJO.add("booking", bookingJA);
        return resJO;
    }
}
