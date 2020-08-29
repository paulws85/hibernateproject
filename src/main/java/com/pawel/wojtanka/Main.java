package com.pawel.wojtanka;

import com.pawel.wojtanka.model.Book;
import com.pawel.wojtanka.model.ComputerBook;
import com.pawel.wojtanka.model.Email;
import com.pawel.wojtanka.model.Message;
import com.pawel.wojtanka.model.MovieInfo;
import com.pawel.wojtanka.service.MovieInfoService;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class Main {
    public static void main(String[] args) {
        final StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();
        try (SessionFactory sessionFactory = new MetadataSources(standardServiceRegistry)
            .buildMetadata().buildSessionFactory()) {
            Session session = sessionFactory.openSession();

//            Query<MovieInfo> query = session.createQuery("from moviesinfo mi where mi.title = 'Leviathan'", MovieInfo.class);
//            //Trzeba pamiętać że moviesinfo jest nazwą tabeli w bazie danych a mi.title odwołuje się do pola w Entity
//            MovieInfo movieInfo = query.uniqueResult();

            Transaction transaction = session.beginTransaction();// transakcje są potrzebne żeby zrobić jakiekolwiek zmiany na bazie typu: insert, update, delete
//
//            MovieInfo newMovie = new MovieInfo();
//            newMovie.setTitle("Toy Story");
//            newMovie.setGenre("Anime");
//            newMovie.setDescription("Anime movie");
//            newMovie.setReleaseDate(LocalDate.parse("2002-05-04"));
//
//            session.save(newMovie);
//            transaction.commit();

            MovieInfoService movieInfoService = new MovieInfoService();
//
//            MovieInfo newMovie = new MovieInfo();
//            newMovie.setTitle("W pustyni i w puszczy");
//            newMovie.setGenre("Adventure");
//            newMovie.setDescription("Adventure movie");
//            newMovie.setReleaseDate(LocalDate.parse("2012-05-04"));
//
//            movieInfoService.addMovie(session, newMovie, 3);

//            Map<String, Object> searchParameters = new HashMap<>();
//            searchParameters.put("genre", "Drama");
//            List<MovieInfo> movieInfos = movieInfoService.findMovieBy(session, searchParameters);

//            movieInfoService.removeMovie(session, 4L);

            //Testujemy relacje OneToOne
//            Email email = new Email();
//            Message message = new Message();
//            Message message2 = new Message();
//
//            email.setSubject("Temat test");
//            message.setContent("Content test");
//            message2.setContent("Content test2");
//
//            //email.setMessage(message);//nie trzeba ustawiać message ponieważ relacja OneToOne(mappedBy = "email") powoduje że podczas zapisu w Email będzie zapisywane rekordy w Message
//            message.setEmail(email);
//
//            session.save(email);
//            session.save(message);
//            session.save(message2);

            //Testujemy relacje OneToMany wersja 1, bez @JoinColumn
//            Email email = new Email();
//
//            List<Message> messages = new ArrayList<>();
//            Message message1 = new Message();
//            Message message2 = new Message();
//
//            messages.add(message1);
//            messages.add(message2);
//
//            email.setSubject("Temat test");
//            message1.setContent("Content test");
//            message2.setContent("Content test2");
//
//            message1.setEmail(email);
//            message2.setEmail(email);
//
//            session.save(email);
//            session.save(message1);
//            session.save(message2);

            //Testujemy relacje OneToMany wersja z @JoinColumn
//            Email email = new Email();
//
//            List<Message> messages = new ArrayList<>();
//            Message message1 = new Message();
//            Message message2 = new Message();
//
//            messages.add(message1);
//            messages.add(message2);
//
//            email.setSubject("Temat test");
//            message1.setContent("Content test");
//            message2.setContent("Content test2");
//
//            email.setMessage(messages);
//
//            session.save(email);
//            session.save(message1);
//            session.save(message2);

            //Testy dziedziczenia SINGLE_TABLE (czyli encje dziedziczące są umieszczne w tej samej tabeli):
            //@Inheritance(strategy = InheritanceType.SINGLE_TABLE) - jest to wartość domyślna
            Book book = new Book();
            book.setTitle("Krzyżacy");
            session.save(book);

            ComputerBook computerBook1 = new ComputerBook();
            computerBook1.setTitle("Jaz dwa trzy");
            computerBook1.setProgrammingLang("PL");
            computerBook1.setReleaseDate(new Date());

            ComputerBook computerBook2 = new ComputerBook();
            computerBook2.setTitle("Przyjaciele");
            computerBook2.setProgrammingLang("ENG");
            computerBook2.setReleaseDate(new Date());

            session.save(computerBook1);
            session.save(computerBook2);

            //Testy dziedziczenia JOINED (czyli encje dziedziczące są umieszczne w osobnej tabeli tylko, że w tych tabelach zanjdują się kolumny z encji dziedziczącej a w pierwszej tabeli z encji bazowej
            //Róznica polega na tym że w encji bazowej zmieniamy typ:
            //@Inheritance(strategy = InheritanceType.JOINED)
            //anjbardziej optymalne ze wszystkich metod dziedziczenia

            //Testy dziedziczenia TABLE_PER_CLASS (czyli encje dziedziczące są umieszczne w osobnej tabeli tylko, że w tych tabelach zanjdują się kolumny z encji dziedziczącej a w pierwszej tabeli z encji bazowej
            //Róznica polega na tym że w encji bazowej zmieniamy typ:
            //@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
            //nie jest przeważnie używany: duplikowanie kolumn i uzupełnianie nullami w klasie

            transaction.commit();
            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}