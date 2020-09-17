package ru.itis.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.models.User;

@Controller
@Slf4j
public class ErrorController {

    @GetMapping("/error")
    public ModelAndView getErrorPage() {
        try {
            ModelAndView modelAndView = new ModelAndView("error");
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
}
