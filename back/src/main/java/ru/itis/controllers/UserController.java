package ru.itis.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dtos.NewUserDto;
import ru.itis.models.User;
import ru.itis.services.UserService;

@Controller
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/signUp")
    public ModelAndView getSignUpPage() {

        try {
            ModelAndView modelAndView = new ModelAndView("signUp");
            Object object =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (object instanceof User) {
                modelAndView.addObject("user", (User)object);
            } else {
                modelAndView.addObject("user", null);
            }
            return modelAndView;
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ModelAndView("error");
        }
    }

    @GetMapping("/login")
    public ModelAndView getLoginPage() {
        try {
            ModelAndView modelAndView = new ModelAndView("login");
            Object object =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (object instanceof User) {
                modelAndView.addObject("user", (User)object);
            } else {
                modelAndView.addObject("user", null);
            }
            return modelAndView;
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ModelAndView("error");
        }
    }

    @PostMapping("/signUp")
    public String signUp(NewUserDto newUserDto) {

        try {
            userService.create(newUserDto);
            return "redirect:/login";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:/error";
        }
    }
 }
