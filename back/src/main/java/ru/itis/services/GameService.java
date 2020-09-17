package ru.itis.services;

import ru.itis.dtos.NewGameDto;
import ru.itis.dtos.UpdatedGameDto;
import ru.itis.models.Game;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface GameService {
    List<Game> getAll();
    Game create(NewGameDto newGameDto);
    Game getById(Long id);
    Game edit(UpdatedGameDto updatedGameDto);
    Game delete(Long id);
    public InputStream getImageInputStream(String imageName);
}
