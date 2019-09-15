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
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author sardorrokhillaev
 */
@Entity
@Table(name="booking")
public class Booking implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    
    @JoinColumn(name = "film_id", referencedColumnName = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Films filmId;
    
    @Pattern(regexp = "[0-9]{10}", message = "wrong phoneNumber format")
    @Column(name = "phone_number")
    private String phoneNumber;
    
    @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")
    @Size(max = 2147483647)
    @Column(name = "email")
    private String email;
        
    @Basic(optional = false)
    @Column(name = "show_day")
    @Temporal(TemporalType.DATE)
    private Date showDay;
    
    @Basic(optional = false)
    @Column(name = "show_time")
    private String showTime;
    
    @Column(name = "place")
    private Integer place;
    
    @Column(name = "hall")
    private Integer hall;
    
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
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmail() {
        return email;
    }
    
    public Date getShowDay() {
        return showDay;
    }

    public void setShowDay(Date showDay) {
        this.showDay = showDay;
    }
    
    public String getShowTime() {
        return showTime;
    }

    public void setShowTime(String showTime) {
        this.showTime = showTime;
    }
    
    public Integer getPlace() {
        return place;
    }

    public void setPlace(Integer place) {
        this.place = place;
    }
    
    public Integer getHall() {
        return hall;
    }
    
    public void setHall(Integer hall) {
        this.hall = hall;
    }
    
    public JsonObject toJson() {
        JsonObject bookingJO = new JsonObject();
        bookingJO.addProperty("id", this.id);
        bookingJO.addProperty("phoneNumber", this.phoneNumber);
        bookingJO.addProperty("email", this.email);
        bookingJO.addProperty("showDay", this.showDay == null ? 0 : this.showDay.getTime());
        bookingJO.addProperty("showTime", this.showTime);
        if(this.filmId!=null){
            if(this.filmId.getName()!=null){
                bookingJO.add("film",  this.filmId == null ? new JsonObject() : this.filmId.toJson());
            }
        }else{
            bookingJO.add("film",  null);
        }
        bookingJO.addProperty("place", this.place);
        bookingJO.addProperty("hall", this.hall);
        return bookingJO;
    }
 }