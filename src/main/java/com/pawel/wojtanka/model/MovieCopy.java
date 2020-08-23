package com.pawel.wojtanka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name ="moviescopies")
public class MovieCopy {

    @Id
    @GeneratedValue
    private Long copyId;

    @Column(nullable = false)
    private Long movieInfoId;

    @Column(nullable = false)
    private Boolean isRented;

    @Column(nullable = false)
    private Integer rentedTimes;

    @Column
    private Integer rentedTo;

    public MovieCopy() {

    }

    public Long getCopyId() {
        return copyId;
    }

    public void setCopyId(Long copyId) {
        this.copyId = copyId;
    }

    public Long getMovieInfoId() {
        return movieInfoId;
    }

    public void setMovieInfoId(Long movieInfoId) {
        this.movieInfoId = movieInfoId;
    }

    public Boolean getRented() {
        return isRented;
    }

    public void setRented(Boolean rented) {
        isRented = rented;
    }

    public Integer getRentedTimes() {
        return rentedTimes;
    }

    public void setRentedTimes(Integer rentedTimes) {
        this.rentedTimes = rentedTimes;
    }

    public Integer getRentedTo() {
        return rentedTo;
    }

    public void setRentedTo(Integer rentedTo) {
        this.rentedTo = rentedTo;
    }

}