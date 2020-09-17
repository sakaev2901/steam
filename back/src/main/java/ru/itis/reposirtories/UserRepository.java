package ru.itis.reposirtories;

import ru.itis.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
