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
public class AccessDeniedException extends BookingTicketGlobalException {

    public AccessDeniedException(LogMessage message) {
        super(message);
        
    }

}
