package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfig;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import ru.itis.models.User;
import ru.itis.reposirtories.UserRepository;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FreeMarkerConfigurer freeMarkerConfigurer;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user != null) {
            Map<String, Object> map = new HashMap<>();
            map.put("user", user);
            freeMarkerConfigurer.setFreemarkerVariables(map);
            return user;
        }
        throw new UsernameNotFoundException("User '" + s + "' not found");
    }
}
