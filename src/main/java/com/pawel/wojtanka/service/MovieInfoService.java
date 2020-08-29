package com.pawel.wojtanka.service;

import com.pawel.wojtanka.model.MovieInfo;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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

    public void removeMovie(Session session, Long id) {
        Transaction transaction = session.beginTransaction();

        String findQuery = "from moviesinfo mi where mi.movieInfoId=:id";
        Query<MovieInfo> query = session.createQuery(findQuery);
        query.setParameter("id", id);

        try {
            MovieInfo movieInfo = query.uniqueResult(); //w tym przypadku gdy nie znajdzie rekordu należy obsłużyć wyjatek NullPointerException
            session.delete(movieInfo);
        } catch (IllegalArgumentException e) {
            System.out.println("Chcesz usunąć nulla");
            e.printStackTrace();
        }

//        //Druga metoda odnajdowania rekordu (odnajduje tylko po id, a jeśli nie znajdzie to wówczas zwraca nulla)
//        MovieInfo movieInfo = session.find(MovieInfo.class, id);
//        if (Objects.nonNull(movieInfo)) {
//            session.delete(movieInfo);
//        }

        transaction.commit();
    }

    public List<MovieInfo> findMovieBy(Session session, Map<String, Object> searchParameters) {
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

    public List<MovieInfo> findByCriteria(Map<MovieInfoCriteria, Object> criteria, Session session) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<MovieInfo> criteriaQuery = criteriaBuilder.createQuery(MovieInfo.class);
        Root<MovieInfo> root = criteriaQuery.from(MovieInfo.class);
        criteriaQuery.select(root).where(titlePredicate((String) criteria.get(MovieInfoCriteria.TITLE), criteriaBuilder, root),
            descriptionPredicate((String) criteria.get(MovieInfoCriteria.DESCRIPTION), criteriaBuilder, root),
            genrePredicate((String) criteria.get(MovieInfoCriteria.GENRE), criteriaBuilder, root),
            dateFromPredicate((LocalDate) criteria.get(MovieInfoCriteria.RELEASE_DATE_FROM), criteriaBuilder, root),
            dateToPredicate((LocalDate) criteria.get(MovieInfoCriteria.RELEASE_DATE_TO), criteriaBuilder, root));
        Query<MovieInfo> query = session.createQuery(criteriaQuery);
        return query.getResultList();
    }

    private Predicate titlePredicate(String title, CriteriaBuilder criteriaBuilder, Root<MovieInfo> root) {
        if (Objects.nonNull(title)) {
            return criteriaBuilder.equal(root.get("title"), title);
        }
        return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }

    private Predicate descriptionPredicate(String description, CriteriaBuilder criteriaBuilder, Root<MovieInfo> root) {
        if (Objects.nonNull(description)) {
            return criteriaBuilder.equal(root.get("description"), description);
        }
        return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }

    private Predicate genrePredicate(String genre, CriteriaBuilder criteriaBuilder, Root<MovieInfo> root) {
        if (Objects.nonNull(genre)) {
            return criteriaBuilder.equal(root.get("genre"), genre);
        }
        return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }

    private Predicate dateFromPredicate(LocalDate dateFrom, CriteriaBuilder criteriaBuilder, Root<MovieInfo> root) {
        if (Objects.nonNull(dateFrom)) {
            return criteriaBuilder.greaterThanOrEqualTo(root.get("releaseDate"), dateFrom);
        }
        return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }

    private Predicate dateToPredicate(LocalDate dateTo, CriteriaBuilder criteriaBuilder, Root<MovieInfo> root) {
        if (Objects.nonNull(dateTo)) {
            return criteriaBuilder.lessThanOrEqualTo(root.get("releaseDate"), dateTo);
        }
        return criteriaBuilder.isTrue(criteriaBuilder.literal(true));
    }

}
