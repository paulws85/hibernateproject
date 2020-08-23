package com.pawel.wojtanka.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name = "rents")
public class Rent {

    @Id
    @GeneratedValue
    private Long rentId;

    @Column(nullable = false)
    private Long rentedMovieId;

    @Column(nullable = false)
    private Long customer;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(precision = 2, nullable = false)
    private Double rentPricePerDay;

    @Column(precision = 2)
    private Double totalPrice;

    @Column(nullable = false)
    private LocalDate rentedDate;

    @Column
    private LocalDate returnedDate;

    public Rent() {

    }

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    public Long getRentedMovieId() {
        return rentedMovieId;
    }

    public void setRentedMovieId(Long rentedMovieId) {
        this.rentedMovieId = rentedMovieId;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(Long customer) {
        this.customer = customer;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getRentPricePerDay() {
        return rentPricePerDay;
    }

    public void setRentPricePerDay(Double rentPricePerDay) {
        this.rentPricePerDay = rentPricePerDay;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getRentedDate() {
        return rentedDate;
    }

    public void setRentedDate(LocalDate rentedDate) {
        this.rentedDate = rentedDate;
    }

    public LocalDate getReturnedDate() {
        return returnedDate;
    }

    public void setReturnedDate(LocalDate returnedDate) {
        this.returnedDate = returnedDate;
    }

}