/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.entities;

import com.google.gson.JsonObject;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author sardorrokhillaev
 */
@Entity
@Table(name="schedule")
public class Schedule implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Films filmId;
    
    @JoinColumn(name = "cinema_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Cinemas cinemaId;
    
    @Basic(optional = false)
    @Column(name = "show_time")
    @Temporal(TemporalType.TIMESTAMP)
    //@JsonDeserialize(using = CustomJsonDateDeserializer.class)
    private Date showTime;
    
    @Column(name="city")
    private String city;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Films getFilmId() {
        return filmId;
    }

    public void setFilmId(Films filmId) {
        this.filmId = filmId;
    }

    public Cinemas getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(Cinemas cinemaId) {
        this.cinemaId = cinemaId;
    }
    
    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public JsonObject toJson() {
        JsonObject scheduleJO = new JsonObject();
        scheduleJO.addProperty("id", this.id);
        scheduleJO.add("filmId",  this.filmId == null ? new JsonObject() : this.filmId.toJson());
        scheduleJO.add("cinemaId",  this.cinemaId == null ? new JsonObject() : this.cinemaId.toJson());
        scheduleJO.addProperty("showTime", this.showTime == null ? 0 : this.showTime.getTime());
        scheduleJO.addProperty(city, this.city);
        return scheduleJO;
    }
}
