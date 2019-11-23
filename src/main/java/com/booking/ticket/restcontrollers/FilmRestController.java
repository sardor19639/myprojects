/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.restcontrollers;

import com.booking.ticket.controllers.BookingController;
import com.booking.ticket.controllers.FilmController;
import com.booking.ticket.entities.Booking;
import com.booking.ticket.entities.Films;
import com.booking.ticket.wrapper.SortWrapper;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.concurrent.TimeoutException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author sardorrokhillaev
 */
@RestController
@RequestMapping(value = "films")
@Validated
public class FilmRestController {
    
    @Autowired
    private FilmController filmController;
    
    @Autowired
    private BookingController bookingController;
    
    @RequestMapping(value = "/",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> getFilms(
        @RequestParam(value = "filter", defaultValue = "") String filterType,
        @RequestParam(value = "name", required = false) String name,
        @RequestParam(value = "genre", required = false) String genre) {
        SortWrapper wrapper = SortWrapper.newBuilder()
            .setFilterType(filterType)
            .setName(name)
            .setGenre(genre)
            .build();
        return ResponseEntity.ok(filmController.getFilms(wrapper));
    }
    
    @RequestMapping(value = "/schedule",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> getSchedule(
        @RequestParam(value = "showDate", required = true) Date showDate,
        @RequestParam(value = "city", required = true) String city,
        @RequestParam(value = "filmId", required = true) Integer filmId) {
        SortWrapper wrapper = SortWrapper.newBuilder()
            .setCity(city)
            .setShowTime(showDate)
            .setFilmId(filmId)
            .build();
        return ResponseEntity.ok(filmController.getSchedule(wrapper));
    }
    
    @RequestMapping(value = "/booking",
        method = RequestMethod.POST,
        produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> createBooking (@RequestBody Booking user){
        return ResponseEntity.ok(bookingController.createBooking(user));
    }
    
    @RequestMapping(value = "/booking/bookings",
        method = RequestMethod.GET,
        produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> getBookings(
        @RequestParam(value = "filter", defaultValue = "") String filterType,
        @RequestParam(value = "number", required = false) Integer number,
        @RequestParam(value="dateFrom", required = false) Date dateFrom,
        @RequestParam(value="dateTo", required = false) Date dateTo,
        @RequestParam(value = "film", required = false) Integer film) {
        SortWrapper wrapper = SortWrapper.newBuilder()
            .setFilterType(filterType)
            .setNumber(number)
            .setDateFrom(dateFrom)
            .setDateTo(dateTo)
            .setFilmId(film)
            .build();
        return ResponseEntity.ok(bookingController.getBookings(wrapper));
    }
    
     @RequestMapping(value = "/film",
        method = RequestMethod.PUT,
        produces = "application/json;charset=UTF-8")
    public ResponseEntity<String> updateOwner(@RequestBody Films film) throws TimeoutException, SocketTimeoutException, SocketException {
        return ResponseEntity.ok(filmController.updateFilm(film));
    }
}
