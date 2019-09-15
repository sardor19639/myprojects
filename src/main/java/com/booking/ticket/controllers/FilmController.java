/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.controllers;

import com.booking.ticket.wrapper.SortWrapper;
import com.google.gson.JsonObject;
import com.booking.ticket.logger.LogMessage;
import com.booking.ticket.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.booking.ticket.logger.BookingTicketLogger;

/**
 *
 * @author sardorrokhillaev
 */
@Component
public class FilmController {
    
    private final BookingTicketLogger logger = BookingTicketLogger.getInstance();
    
    private static final String CLASS_NAME = FilmController.class.getSimpleName();
    
    @Autowired
    private FilmService filmService;
    
    public String getFilms(SortWrapper wrapper) {
        final LogMessage message = LogMessage.newBuilder()
                .setClassName(CLASS_NAME)
                .setMethodName("getFilms")
                .setRequest("films/ GET")
                .setRequestBody(wrapper)
                .build();
        JsonObject res = filmService.getFilms(wrapper, message);
        logger.onSuccessLog(message);
        return res.toString();
    }
    
    public String getSchedule(SortWrapper wrapper) {
        final LogMessage message = LogMessage.newBuilder()
                .setClassName(CLASS_NAME)
                .setMethodName("getSchedule")
                .setRequest("films/schedule GET")
                .setRequestBody(wrapper)
                .build();
        JsonObject res = filmService.getSchedule(wrapper, message);
        logger.onSuccessLog(message);
        return res.toString();
    }
    
}
