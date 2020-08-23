package com.pawel.wojtanka;

import com.pawel.wojtanka.model.MovieInfo;
import com.pawel.wojtanka.service.MovieInfoService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
//
//            MovieInfo movieInfo = query.uniqueResult();

//            Transaction transaction = session.beginTransaction();// transakcje są potrzebne żeby zrobić jakiekolwiek zmiany na bazie typu: insert, update, delete
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

            MovieInfo newMovie = new MovieInfo();
            newMovie.setTitle("W pustyni i w puszczy");
            newMovie.setGenre("Adventure");
            newMovie.setDescription("Adventure movie");
            newMovie.setReleaseDate(LocalDate.parse("2012-05-04"));

            movieInfoService.addMovie(session, newMovie, 3);

//            Map<String, Object> searchParameters = new HashMap<>();
//            searchParameters.put("genre", "Drama");
//            List<MovieInfo> movieInfos = movieInfoService.findMovie(session, searchParameters);

            session.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}