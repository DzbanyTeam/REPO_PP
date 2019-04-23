package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.Difficulty;

import java.util.List;

public interface IDifficultyService {

    Difficulty getDifficultyById(Long id);
    boolean saveDifficulty(Difficulty difficulty);
    boolean deleteDifficulties(List<Long> ids);
    List<Difficulty> getAllDifficulties();
}
