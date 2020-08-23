package com.pawel.wojtanka.service;

import com.pawel.wojtanka.model.MovieInfo;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class MovieInfoService {

    public void addMovie(Session session, MovieInfo movieInfo, int numberOfCopies) {
        Transaction transaction = session.beginTransaction();
        session.save(movieInfo);
        transaction.commit();

        MovieInfoCopyService movieInfoCopyService = new MovieInfoCopyService();
        for (int counter = 0; counter < numberOfCopies; counter++) {
            movieInfoCopyService.addMovieCopy(session, movieInfo.getMovieInfoId());
        }
    }

    public List<MovieInfo> findMovie(Session session, Map<String, Object> searchParameters) {
        StringBuilder builtQuery = new StringBuilder("from moviesinfo mi");

        Map<String, Object> filteredSearchParameters = searchParameters.entrySet().stream()
            .filter(value -> value.getValue() != null)
            .filter(entry -> entry.getKey().equals("title")
                || entry.getKey().equals("genre")
                || entry.getKey().equals("releaseDate")
                || entry.getKey().equals("description"))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));


        if (filteredSearchParameters.size() >= 1) {
            builtQuery.append(" where");
        }

        int counter = 0;
        for (Map.Entry<String, Object> entrySet : filteredSearchParameters.entrySet()) {
            if (counter == 0) {
                builtQuery
                    .append(" ")
                    .append(entrySet.getKey())
                    .append("=:")
                    .append(entrySet.getKey());
            } else {
                builtQuery.append(" and ")
                    .append(entrySet.getKey())
                    .append("=:")
                    .append(entrySet.getKey());
            }
            counter++;
        }

        Query<MovieInfo> query = session.createQuery(builtQuery.toString(), MovieInfo.class);

        for (Map.Entry<String, Object> entrySet : filteredSearchParameters.entrySet()) {
            switch (entrySet.getKey()) {
                case "title":
                    query.setParameter("title", entrySet.getValue());
                    break;
                case "genre":
                    query.setParameter("genre", entrySet.getValue());
                    break;
                case "releaseDate":
                    query.setParameter("releaseDate", entrySet.getValue());
                    break;
                case "description":
                    query.setParameter("description", entrySet.getValue());
                    break;
            }
        }

        return query.getResultList();
    }

}
