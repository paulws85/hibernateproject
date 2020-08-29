package com.pawel.wojtanka.model;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "DISCRIMINATOR",
    discriminatorType = DiscriminatorType.INTEGER)
@DiscriminatorValue("1")
public class Book {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String title;

    @Column
    @Temporal(value = TemporalType.TIME) //trzeba zwrocić na to uwagę czy poprzednie rekordy nie posiadją już samej daty, ponieważ wówczas wyzeruje czas bo poprzednie rekordy zawieraja datę
    Date releaseDate;

}