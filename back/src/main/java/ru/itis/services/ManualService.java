package ru.itis.services;

import ru.itis.dtos.NewManualDto;
import ru.itis.dtos.UpdatedManualDto;
import ru.itis.models.Manual;

import java.util.List;

public interface ManualService {
    Manual create(NewManualDto newManualDto);
    Manual edit(UpdatedManualDto updatedManualDto);
    Manual delete(Long id);
    Manual findById(Long id);
    List<Manual> findAllGameManuals(Long gameId);
}
