/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.repositories;

import com.booking.ticket.entities.Films;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author sardorrokhillaev
 */
public interface FilmRepository extends CrudRepository<Films, Integer> {
    
    @Query(value = "select distinct on(schedule.film_id) films.id , films.name, films.country, films.director, films.genre,films.description from films\n"
      + "join schedule \n" 
      + "on schedule.film_id = films.id\n"
      + "where (lower(films.name) like lower(concat('%',:name, '%')) and schedule.show_time >= NOW()) or (lower(films.name) like lower(concat('%',:name, '%')) and schedule.show_time is null)\n", nativeQuery = true)
    List<Films> findFilmsByName(@Param("name") String name); 
    
    @Query(value = "select distinct on(schedule.film_id) films.id , films.name, films.country, films.director, films.genre,films.description from films\n"
      + "join schedule \n" 
      + "on schedule.film_id = films.id\n"
      + "where schedule.show_time >= NOW() or schedule.show_time is null\n", nativeQuery = true)
    List<Films> findNewFilms();
    
    @Query(value = "select distinct on(schedule.film_id) films.id , films.name, films.country, films.director, films.genre,films.description from films\n"
      + "join schedule \n" 
      + "on schedule.film_id = films.id\n"
      + "where (lower(films.genre) like lower(concat('%',:genre, '%')) and schedule.show_time >= NOW()) or (lower(films.name) like lower(concat('%',:genre, '%')) and schedule.show_time is null)\n", nativeQuery = true)
    List<Films> findFilmsByGenre(@Param("genre")String genre);
    
    Films findFilmsById(Integer id);
    
    @Query(value = "select * from films where id in (\n"
      + "select film_id from\n"
      + "(select count(booking.film_id) as count,\n"
      + "booking.film_id  from booking\n"
      + "group by booking.film_id order by count desc  limit 2) as a)\n"
      , nativeQuery = true)
    List<Films> findFilmsByPopularity();
       
}
