package com.pawel.wojtanka.service;

import com.pawel.wojtanka.model.MovieCopy;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MovieInfoCopyService {

    public void addMovieCopy(Session session, Long movieId) {
        Transaction transaction = session.beginTransaction();

        MovieCopy movieCopy = new MovieCopy();
        movieCopy.setMovieInfoId(movieId);
        movieCopy.setRented(false);
        movieCopy.setRentedTimes(0);
        movieCopy.setRentedTo(null);

        session.save(movieCopy);
        transaction.commit();
    }

}
