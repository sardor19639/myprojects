/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.controllers;

import com.booking.ticket.entities.Booking;
import com.booking.ticket.enums.Enums;
import com.booking.ticket.logger.GMoikaLogger;
import com.booking.ticket.logger.LogMessage;
import com.booking.ticket.services.BookingService;
import com.booking.ticket.wrapper.SortWrapper;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author sardorrokhillaev
 */
@Component
public class BookingController {
    
    private final GMoikaLogger logger = GMoikaLogger.getInstance();
    
    private static final String CLASS_NAME = FilmController.class.getSimpleName();
    
    @Autowired
    private BookingService bookingService;
    
    public String createBooking( Booking booking) {
        LogMessage message = LogMessage.newBuilder()
                .setClassName(CLASS_NAME)
                .setMethodName("creatBooking")
                .setRequest("films/booking POST")
                .setRequestBody(booking)
                .build();
        final JsonObject result = bookingService.createBooking(booking, message);
        logger.onSuccessLog(message);
        return result.toString();
    }
    
    public String getBookings(SortWrapper wrapper) {
        final LogMessage message = LogMessage.newBuilder()
                .setClassName(CLASS_NAME)
                .setMethodName("getBookings")
                .setRequest("films/booking/bookings GET")
                .setRequestBody(wrapper)
                .build();
        JsonObject res = bookingService.getBookings(wrapper, message);
        logger.onSuccessLog(message);
        return res.toString();
    }
    
}
