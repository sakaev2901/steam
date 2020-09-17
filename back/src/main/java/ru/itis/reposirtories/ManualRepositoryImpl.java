package ru.itis.reposirtories;

import org.springframework.stereotype.Repository;
import ru.itis.models.Game;
import ru.itis.models.Manual;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class ManualRepositoryImpl implements ManualRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Manual findById(Long id) {
        return entityManager.createQuery("select c from Manual c where c.id = :id", Manual.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Manual save(Manual model) {
        entityManager.persist(model);
        return model;
    }

    @Override
    public Manual update(Manual model) {
        return entityManager.merge(model);
    }

    @Override
    public Manual delete(Manual model) {
        entityManager.remove(model);
        return model;
    }

    @Override
    public List<Manual> findAll() {
        return null;
    }


    @Override
    public List<Manual> findAllGameManuals(Game game) {
        return entityManager.createQuery("select c from Manual c where c.game = :game", Manual.class)
                .setParameter("game", game)
                .getResultList();
    }
}
