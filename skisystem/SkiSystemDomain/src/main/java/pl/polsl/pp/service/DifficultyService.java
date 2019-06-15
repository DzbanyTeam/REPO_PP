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

/**
 * Usługa wykonująca operacje na obiektach typu Difficulty
 */
public class DifficultyService implements IDifficultyService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli difficulties
     */
    @Autowired
    @Qualifier("difficultyRepository")
    private DifficultyRepository difficultyRepository;

    /**
     * Zwraca obiekt Difficulty z danym id
     * @param id Difficulty id
     * @return Difficulty
     */
    @Override
    public Difficulty getDifficultyById(Long id) {
        return difficultyRepository.findById(id).get();
    }

    /**
     * Dodaje dany obiekt Difficulty do bazy danych
     * @param difficulty obiekt Difficulty do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean saveDifficulty(Difficulty difficulty) {
        try{
            difficultyRepository.save(difficulty);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Usuwa obiekty Difficulty z podanymi id z bazy danych
     * @param ids lista Difficulty id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteDifficulties(List<Long> ids) {
        try{
            ids.forEach(id -> difficultyRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów Difficulty
     * @return lista Difficulty
     */
    @Override
    public List<Difficulty> getAllDifficulties() {
        List<Difficulty> difficultyList = new ArrayList<>();
        difficultyRepository.findAll().forEach(d -> difficultyList.add(d));
        return difficultyList;
    }
}
