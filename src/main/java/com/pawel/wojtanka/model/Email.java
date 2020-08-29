package com.pawel.wojtanka.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Setter;

@Entity
@Setter
public class Email {

    @Id
    @GeneratedValue
    Long id;

    @Column
    String subject;

//    @OneToOne(mappedBy = "email") //klucz obcy bedzie po stronie message (przeważnie tam gdzie jest many i wskazuje na pole z drugiej Encji) (w tej drugiej tabelce niż Email, czyli Message), relacja jest zarzadzana w drugiej encji, jest to relacja jednokierunkowa
//    Message message;

//    @OneToMany //Tworzy tabele posredniczca zawierającą mapowanie między tabelami message i email
//        List<Message> message;

    @OneToMany(mappedBy = "email") //nie tworzy tabeli pośredniczącej między tabelami
    List<Message> message;

//    @OneToMany
//        @JoinColumn(name = "email_id") //drugi sposób wygeneruje kolumnę o podanej nazwie w tabeli Message, z kluczem obcym
//        List<Message> message;

}
