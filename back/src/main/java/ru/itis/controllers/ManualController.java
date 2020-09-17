package ru.itis.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dtos.NewManualDto;
import ru.itis.dtos.UpdatedManualDto;
import ru.itis.models.Manual;
import ru.itis.models.User;
import ru.itis.services.GameService;
import ru.itis.services.ManualService;

@Controller
@Slf4j
public class ManualController {

    @Autowired
    private ManualService manualService;
    @Autowired
    private GameService gameService;

    @GetMapping("/manual/editor/{gameId}")
    public ModelAndView getEditorPage(@PathVariable Long gameId) {
        try {
            ModelAndView modelAndView = new ModelAndView("editor");
            modelAndView.addObject("game", gameService.getById(gameId));
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

    @GetMapping("/manual/{id}")
    public ModelAndView getManualPage(@PathVariable Long id) {

        try {
            ModelAndView modelAndView = new ModelAndView("manual");
            modelAndView.addObject("manual", manualService.findById(id));
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

    @GetMapping("/manual/edit/{id}")
    public ModelAndView getManualEditPage(@PathVariable Long id) {
        try {
            ModelAndView modelAndView = new ModelAndView("manualEdit");
            modelAndView.addObject("manual", manualService.findById(id));
            return modelAndView;
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ModelAndView("error");
        }

    }

    @PostMapping("/manual/edit")
    public String editManual(UpdatedManualDto updatedManualDto) {

        try {
            manualService.edit(updatedManualDto);
            return "redirect:/manual/" + updatedManualDto.getId();
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:/error";
        }

    }

    @PostMapping("/manual/create")
    public String createManual(@RequestBody NewManualDto newManualDto) {

        try {
            Manual manual = manualService.create(newManualDto);
            return "redirect:/manual/" + manual.getId();
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:/error";
        }
    }


}
