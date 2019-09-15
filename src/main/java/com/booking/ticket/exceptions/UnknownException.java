/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.exceptions;

import com.booking.ticket.logger.LogMessage;

/**
 *
 * @author strogiy.otec
 */
public class UnknownException extends BookingTicketGlobalException {
        public UnknownException(LogMessage message){
            super(message);
        }
}
