package ru.itis.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.dtos.NewRatingDto;
import ru.itis.services.RatingService;

@RestController
@Slf4j
public class RestRatingController {

    @Autowired
    private RatingService ratingService;

    @PostMapping("/api/rating")
    public ResponseEntity<?> setRating(NewRatingDto newRatingDto) {
        try {
            ratingService.addRating(newRatingDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }
}
