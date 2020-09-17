package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.itis.dtos.NewManualDto;
import ru.itis.dtos.UpdatedGameDto;
import ru.itis.dtos.UpdatedManualDto;
import ru.itis.models.Manual;
import ru.itis.models.User;
import ru.itis.reposirtories.GameRepository;
import ru.itis.reposirtories.ManualRepository;

import java.util.List;

@Service
public class ManualServiceImpl implements ManualService {

    @Autowired
    private ManualRepository manualRepository;
    @Autowired
    private GameRepository gameRepository;

    @Override
    public Manual create(NewManualDto newManualDto) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return manualRepository.save(Manual.builder()
                .title(newManualDto.getTitle())
                .htmlText(newManualDto.getHtmlText())
                .game(gameRepository.findById(newManualDto.getGameId()))
                .user(user)
                .build());
    }

    @Override
    public Manual edit(UpdatedManualDto updatedManualDto) {
        Manual manual = manualRepository.findById(updatedManualDto.getId());
        manual.setTitle(updatedManualDto.getTitle());
        manual.setHtmlText(updatedManualDto.getHtmlText());
        return manualRepository.update(manual);
    }

    @Override
    public Manual delete(Long id) {
        return manualRepository.delete(manualRepository.findById(id));
    }

    @Override
    public Manual findById(Long id) {
        return manualRepository.findById(id);
    }

    @Override
    public List<Manual> findAllGameManuals(Long gameId) {
        return manualRepository.findAllGameManuals(gameRepository.findById(gameId));
    }
}
