package ru.itis.controllers;

import com.sun.org.apache.xpath.internal.operations.Mod;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import ru.itis.dtos.NewGameDto;
import ru.itis.dtos.UpdatedGameDto;
import ru.itis.models.Game;
import ru.itis.models.User;
import ru.itis.services.GameService;
import sun.nio.ch.IOUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("/games")
    public ModelAndView getGames() {
        try {
            ModelAndView modelAndView = new ModelAndView("games");
            modelAndView.addObject("games", gameService.getAll());
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

    @GetMapping("/game")
    public ModelAndView getCreatingGamePage() {
        try {
            ModelAndView modelAndView = new ModelAndView("createGame");
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

    @PostMapping("/game")
    public String createGame(NewGameDto newGameDto) {
        try {
            Game game = gameService.create(newGameDto);
            return "redirect:/games";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:/error";
        }
    }

    @GetMapping("/game/{id}")
    public ModelAndView getGame(@PathVariable Long id) {

        try {
            ModelAndView modelAndView = new ModelAndView("game");
            Game game = gameService.getById(id);
            modelAndView.addObject("game", game);
            modelAndView.addObject("manuals", game.getManuals());
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

    @GetMapping("/game/edit/{id}")
    public ModelAndView getEditGamePage(@PathVariable Long id) {

        try {
            ModelAndView modelAndView = new ModelAndView("editGame");
            modelAndView.addObject("game", gameService.getById(id));
            return modelAndView;
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ModelAndView("error");
        }
    }

    @PostMapping("/game/edit")
    public String editGame(UpdatedGameDto updatedGameDto) {

        try {
            Game game = gameService.edit(updatedGameDto);
            return "redirect:/game/" + game.getId();
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:/error";
        }
    }

    @PostMapping("/game/delete")
    public String deleteGame(@RequestParam Long id) {
        try {
            gameService.delete(id);
            return "redirect:/games";
        } catch (Exception e) {
            log.error(e.getMessage());
            return "redirect:/error";
        }
    }

    @GetMapping("/image/{img-name:.+}")
    public void getImage(@PathVariable("img-name") String imageName, HttpServletResponse response) {
        try {
            IOUtils.copy(gameService.getImageInputStream(imageName), response.getOutputStream());
            response.flushBuffer();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
