package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.dtos.NewManualDto;
import ru.itis.dtos.NewRatingDto;
import ru.itis.models.Rating;
import ru.itis.models.User;
import ru.itis.reposirtories.ManualRepository;
import ru.itis.reposirtories.RatingRepository;

@Service
public class RatingServiceImpl implements RatingService {

    @Autowired
    private ManualRepository manualRepository;
    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public Rating addRating(NewRatingDto newRatingDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Rating rating = Rating.builder()
                .value(newRatingDto.getValue())
                .user(user)
                .manual(manualRepository.findById(newRatingDto.getManualId()))
                .build();

        return ratingRepository.save(rating);
    }
}
