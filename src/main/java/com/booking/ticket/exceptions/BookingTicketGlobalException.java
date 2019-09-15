/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.exceptions;

import com.booking.ticket.logger.LogMessage;


/**
 *
 * @author sardorrokhillaev
 */
public abstract class BookingTicketGlobalException extends RuntimeException {
    private LogMessage em;
    public BookingTicketGlobalException(LogMessage error){
        super(error.getCause());
        this.em = error;
    }
    public LogMessage message(){
            return this.em;
        }
}

