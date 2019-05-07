package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Difficulty;
import pl.polsl.pp.repository.DifficultyRepository;
import pl.polsl.pp.service.interfaces.IDifficultyService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DifficultyService implements IDifficultyService {

    @Autowired
    @Qualifier("difficultyRepository")
    private DifficultyRepository difficultyRepository;

    @Override
    public Difficulty getDifficultyById(Long id) {
        return difficultyRepository.findById(id).get();
    }

    @Override
    public boolean saveDifficulty(Difficulty difficulty) {
        try{
            difficultyRepository.save(difficulty);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteDifficulties(List<Long> ids) {
        try{
            ids.forEach(id -> difficultyRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<Difficulty> getAllDifficulties() {
        List<Difficulty> difficultyList = new ArrayList<>();
        difficultyRepository.findAll().forEach(d -> difficultyList.add(d));
        return difficultyList;
    }
}
