/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.booking.ticket.wrapper;

import java.util.Date;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 *
 * @author Sardor Rokhillayev
 */
public class SortWrapper {

    private String name;
    private String filterType;
    private String genre;
    private String city;
    private Date showDate;
    private Integer filmId;
    private Integer number;
    private Date dateFrom;
    private Date dateTo;
    

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.JSON_STYLE)
                .append("filterType", filterType)
                .append("name", name)
                .append("genre", genre)
                .append("city",city)
                .append("showTime", showDate)
                .append("filmId",filmId)
                .append("number", number)
                .append("dateFrom",dateFrom)
                .append("dateTo", dateTo)
                .toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFilterType() {
        return filterType;
    }
    
    public void setFilterType(String filterType) {
        this.filterType = filterType;
    }
    
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    public Date getShowDate() {
        return showDate;
    }

    public void setShowDate(Date showDate) {
        this.showDate = showDate;
    }
    
    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }
    
    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
    
    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }
    
    public Date getDateTo() {
        return dateTo;
    }
    
    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public static Builder newBuilder() {
        return new SortWrapper().new Builder();
    }

    public class Builder {

        public Builder setFilterType(String filterType) {
            SortWrapper.this.filterType = filterType;
            return this;
        }

        public Builder setName(String name) {
            SortWrapper.this.name = name;
            return this;
        }
        
        public Builder setGenre(String genre) {
            SortWrapper.this.genre = genre;
            return this;
        }
        
        public Builder setCity(String city) {
            SortWrapper.this.city = city;
            return this;
        }
        
        public Builder setShowTime(Date showDate) {
            SortWrapper.this.showDate = showDate;
            return this;
        }
        
        public Builder setFilmId(Integer filmId) {
            SortWrapper.this.filmId = filmId;
            return this;
        }
        
        public Builder setNumber(Integer number) {
            SortWrapper.this.number = number;
            return this;
        }
        
        public Builder setDateFrom(Date dateFrom) {
            SortWrapper.this.dateFrom = dateFrom;
            return this;
        }
        
        public Builder setDateTo(Date dateTo) {
            SortWrapper.this.dateTo = dateTo;
            return this;
        }
        
        public SortWrapper build() {
            return SortWrapper.this;
        }
    }
}
