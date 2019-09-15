
package com.booking.ticket.exceptions;

import com.booking.ticket.logger.LogMessage;


public class NoSuchElementInDBException extends BookingTicketGlobalException {
        public NoSuchElementInDBException(LogMessage message){
            super(message);
        }

}