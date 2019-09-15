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
public class EmptyMemCacheException extends BookingTicketGlobalException {
     private final static String EMPTY_MEMCACHE = "No such element stored in memcache";
        public EmptyMemCacheException(LogMessage message){
            super(message);
        }
    
}
