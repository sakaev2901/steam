package ru.itis.reposirtories;

import org.springframework.stereotype.Repository;
import ru.itis.models.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public User findById(Long id) {
        return entityManager.createQuery("select c from User c where c.id = :id", User.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public User save(User model) {
        entityManager.persist(model);
        return model;
    }

    @Override
    public User update(User model) {
        return entityManager.merge(model);
    }

    @Override
    public User delete(User model) {
        entityManager.remove(model);
        return model;
    }

    @Override
    public List<User> findAll() {
        return entityManager.createQuery("select c from User c", User.class)
                .getResultList();
    }

    @Override
    public User findByUsername(String username) {
        return entityManager.createQuery("select c from User c where c.username = :username", User.class)
                .setParameter("username", username)
                .getSingleResult();
    }
}
