/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.repositories;

import com.booking.ticket.entities.Booking;
import com.booking.ticket.entities.Films;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sardorrokhillaev
 */
public interface BookingRepository extends CrudRepository<Booking, Integer> {
    List<Booking> findBookingByShowDayAndShowTimeAndPlaceAndHall(Date showDay,String showTime, Integer place, Integer hall);
    Booking findBookingById(int id);
    List<Booking> findBookingByShowDayBetween(Date dateFrom, Date dateTo);
    List<Booking> findBookingByFilmId(Films film);
    
    
}
