package ru.itis.reposirtories;

import org.springframework.stereotype.Repository;
import ru.itis.models.Rating;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RatingRepositoryImpl implements RatingRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Rating findById(Long aLong) {
        return null;
    }

    @Override
    public Rating save(Rating model) {
        entityManager.persist(model);
        return model;
    }

    @Override
    public Rating update(Rating model) {
        return null;
    }

    @Override
    public Rating delete(Rating model) {
        return null;
    }

    @Override
    public List<Rating> findAll() {
        return null;
    }
}
