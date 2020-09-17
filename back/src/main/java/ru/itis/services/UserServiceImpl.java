package ru.itis.services;

import javafx.util.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.dtos.NewUserDto;
import ru.itis.dtos.UpdatedUserDto;
import ru.itis.models.User;
import ru.itis.reposirtories.UserRepository;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User create(NewUserDto newUserDto) {
        User candidate = userRepository.findByUsername(newUserDto.getUsername());
        if (candidate == null) {
            return userRepository.save(User.builder()
                    .username(newUserDto.getUsername())
                    .password(passwordEncoder.encode(newUserDto.getPassword()))
                    .role("ROLE_USER")
                    .build());
        } else  {
            throw new IllegalStateException("No user");
        }

    }

    @Override
    public User edit(UpdatedUserDto updatedUserDto) {
        User user = userRepository.findById(updatedUserDto.getId());
        user.setUsername(updatedUserDto.getUsername());
        return userRepository.update(user);
    }

    @Override
    public User delete(Long id) {
        return userRepository.delete(userRepository.findById(id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }
}
