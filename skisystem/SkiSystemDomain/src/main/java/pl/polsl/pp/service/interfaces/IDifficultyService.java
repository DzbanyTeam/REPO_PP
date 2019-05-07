package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.Difficulty;

import java.util.List;
import java.util.Map;

public interface IDifficultyService {

    Difficulty getDifficultyById(Long id);
    boolean saveDifficulty(Difficulty difficulty);
    boolean deleteDifficulties(List<Long> ids);
    List<Difficulty> getAllDifficulties();
}
