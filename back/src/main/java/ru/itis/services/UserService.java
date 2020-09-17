package ru.itis.services;


import ru.itis.dtos.NewUserDto;
import ru.itis.dtos.UpdatedUserDto;
import ru.itis.models.User;

import java.util.List;

public interface UserService {

    User getById(Long id);
    User create(NewUserDto newUserDto);
    User edit(UpdatedUserDto updatedUserDto);
    User delete(Long id);
    List<User> findAll();
}
