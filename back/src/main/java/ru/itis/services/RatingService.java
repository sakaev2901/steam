package ru.itis.services;

import ru.itis.dtos.NewManualDto;
import ru.itis.dtos.NewRatingDto;
import ru.itis.models.Rating;

public interface RatingService {
    Rating addRating(NewRatingDto newRatingDto);
}
