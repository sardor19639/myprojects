/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.repositories;

import com.booking.ticket.entities.Films;
import com.booking.ticket.entities.Schedule;
import java.util.Date;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author sardorrokhillaev
 */
public interface ScheduleRepository extends CrudRepository<Schedule, Integer> {
    
    List<Schedule> findScheduleByCityAndFilmIdAndShowTimeBetween(String city,Films filmId,Date showTime1, Date showTime2);
}
