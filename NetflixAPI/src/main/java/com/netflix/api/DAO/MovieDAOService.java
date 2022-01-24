package com.netflix.api.DAO;

import com.netflix.api.models.MovieModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class MovieDAOService {

    @PersistenceContext
    private EntityManager entityManager;

    public long insert(MovieModel movieModel) {
        entityManager.persist(movieModel);
        return movieModel.getId();

    }
}
