package ru.itis.reposirtories;

import org.springframework.stereotype.Repository;
import ru.itis.models.Game;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class GameRepositoryImpl implements GameRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Game findById(Long id) {
        return entityManager.createQuery("select c from Game c where c.id = :id", Game.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public Game save(Game model) {
        entityManager.persist(model);
        return model;
    }

    @Override
    public Game update(Game model) {
        return entityManager.merge(model);
    }

    @Override
    public Game delete(Game model) {
        entityManager.remove(model);
        return model;
    }

    @Override
    public List<Game> findAll() {
        return entityManager.createQuery("select c from Game c", Game.class).getResultList();
    }
}
