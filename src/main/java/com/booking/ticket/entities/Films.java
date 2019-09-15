/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.entities;

import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author sardorrokhillaev
 */
@Entity
@Table(name="films")
public class Films implements Serializable {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="genre")
    private String genre;
    
    @Column(name="country")
    private String country;
       
    @Column(name="director")
    private String director;
    
    @Column(name = "description")
    private String description;
    
    @OneToMany(mappedBy = "filmId", fetch = FetchType.LAZY)
    private Set<Booking> bookingSet;
    
    @OneToMany(mappedBy = "filmId", fetch = FetchType.LAZY)
    private Set<Schedule> scheduleSet;
    
    public Films(){}
    
    public Films(Integer id){
        this.id = id;
    }
     
    public Integer getId(){
        return id;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public String getGenre(){
        return genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getCountry() {
        return country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
    
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public Set<Booking> getBookingSet() {
        return bookingSet;
    }

    public void setBookingSet(Set<Booking> bookingSet) {
        this.bookingSet = bookingSet;
    }

    public Set<Schedule> getScheduleSet() {
        return scheduleSet;
    }

    public void setScheduleSet(Set<Schedule> scheduleSet) {
        this.scheduleSet = scheduleSet;
    }
    
    public JsonObject toJson() {
        JsonObject filmJO = new JsonObject();
        filmJO.addProperty("id", this.id);
        filmJO.addProperty("name", this.name);
        filmJO.addProperty("genre", this.genre);
        filmJO.addProperty("country", this.country);
        filmJO.addProperty("director", this.director);
        filmJO.addProperty("description", this.description);
        return filmJO;
    }
}
