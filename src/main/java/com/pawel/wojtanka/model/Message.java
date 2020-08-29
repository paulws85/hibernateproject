package com.pawel.wojtanka.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.Setter;

@Entity
@Setter
public class Message {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String content;

//    @OneToOne
//    Email email;

    @ManyToOne
        @JoinColumn(name="bla")
    Email email;

}
