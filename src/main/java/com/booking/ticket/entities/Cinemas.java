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
@Table(name="cinemas")
public class Cinemas implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @Column(name="name")
    private String name;
    
    @Column(name="city")
    private String city;
    
    @OneToMany(mappedBy = "cinemaId", fetch = FetchType.LAZY)
    private Set<Places> placeSet;

    @OneToMany(mappedBy = "cinemaId", fetch = FetchType.LAZY)
    private Set<Schedule> scheduleSet;
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Set<Places> getPlaceSet() {
        return placeSet;
    }

    public void setCinemaSet(Set<Places> placeSet) {
        this.placeSet = placeSet;
    }

    public Set<Schedule> getScheduleSet() {
        return scheduleSet;
    }

    public void setScheduleSet(Set<Schedule> scheduleSet) {
        this.scheduleSet = scheduleSet;
    }
    
    public JsonObject toJson() {
        JsonObject cinemaJO = new JsonObject();
        cinemaJO.addProperty("id", this.id);
        cinemaJO.addProperty("name", this.name);
        cinemaJO.addProperty("city", this.city);
        return cinemaJO;
    }
    
}
