package ru.itis.services;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.dtos.NewGameDto;
import ru.itis.dtos.UpdatedGameDto;
import ru.itis.models.Game;
import ru.itis.models.User;
import ru.itis.reposirtories.GameRepository;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Override
    public List<Game> getAll() {
        return gameRepository.findAll();
    }

    @Override
    public Game create(NewGameDto newGameDto){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Game game = Game.builder()
                .description(newGameDto.getDescription())
                .manuals(Collections.emptyList())
                .name(newGameDto.getName())
                .imageName(saveImage(newGameDto.getImg()))
                .user(user)
                .build();
        return gameRepository.save(game);
    }

    public String saveImage(MultipartFile multipartFile) {
        String originalImageName = multipartFile.getOriginalFilename();
        String extensionOfImage = originalImageName.split("\\.")[1];
        String imageNameWithoutExtension = originalImageName.split("\\.")[0];
        String newImageName = imageNameWithoutExtension + RandomStringUtils.randomAlphanumeric(5) + "." + extensionOfImage;
        Path path = Paths.get("C:\\Projects\\steam\\back\\uploads\\" + newImageName);
        try {
            multipartFile.transferTo(path);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        return newImageName;
    }

    @Override
    public Game getById(Long id) {
        return gameRepository.findById(id);
    }

    @Override
    public Game edit(UpdatedGameDto updatedGameDto) {
        Game game = gameRepository.findById(updatedGameDto.getId());
        game.setDescription(updatedGameDto.getDescription());
        game.setImageName(saveImage(updatedGameDto.getImg()));
        game.setName(updatedGameDto.getName());
        return gameRepository.update(game);
    }

    @Override
    public Game delete(Long id) {
        return gameRepository.delete(gameRepository.findById(id));
    }

    @Override
    public InputStream getImageInputStream(String imageName) {
        try {
            return new FileInputStream(new File("C:\\Projects\\steam\\back\\uploads\\" + imageName));
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }

    }
}
