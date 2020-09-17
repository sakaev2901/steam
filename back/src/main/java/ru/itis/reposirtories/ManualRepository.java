package ru.itis.reposirtories;

import ru.itis.models.Game;
import ru.itis.models.Manual;

import java.util.List;

public interface ManualRepository extends CrudRepository<Manual, Long> {
    List<Manual> findAllGameManuals(Game game);
}
