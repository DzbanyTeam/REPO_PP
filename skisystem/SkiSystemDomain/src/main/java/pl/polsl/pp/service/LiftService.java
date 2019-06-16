package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Lift;
import pl.polsl.pp.repository.LiftRepository;
import pl.polsl.pp.service.interfaces.ILiftService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Usługa wykonująca operacje na obiektach typu Lift
 */
public class LiftService implements ILiftService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli lifts
     */
    @Autowired
    @Qualifier("liftRepository")
    private LiftRepository liftRepository;

    /**
     * Zwraca obiekt Lift z danym id
     * @param id Lift id
     * @return Lift
     */
    @Override
    public Lift getLiftById(Long id) {
        return liftRepository.findById(id).get();
    }

    /**
     * Dodaje dany obiekt Lift do bazy danych
     * @param lift obiekt Lift do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean saveLift(Lift lift) {
        try{
            liftRepository.save(lift);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Usuwa obiekty Lift z podanymi id z bazy danych
     * @param ids lista Lift id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteLifts(List<Long> ids) {
        try{
            ids.forEach(id -> liftRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Aktywuje obiekty Lift z podanym id
     * @param ids lista Lift id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean activateLifts(List<Long> ids) {
        return changeActiveStatus(ids, true);
    }

    /**
     * Dezaktywuje obiekty Lift z podanym id
     * @param ids lista Lift id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deactivateLifts(List<Long> ids) {
        return changeActiveStatus(ids, false);
    }

    /**
     * Aktywuje/dezaktywuje obiekty Lift z podanym id
     * @param ids lista Lift id
     * @param newStatus true jeśli obiekty mają być aktywowane, false jeśli mają być dezaktywowane
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @SuppressWarnings("Duplicates")
    private boolean changeActiveStatus(List<Long> ids, boolean newStatus){
        try{
            List<Lift> liftList = (List<Lift>)liftRepository.findAllById(ids);
            liftList.forEach(l -> {
                l.setIsActive(newStatus);
            });
            liftRepository.saveAll(liftList);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów Lift
     * @return lista Lift
     */
    @Override
    public List<Lift> getAllLifts() {
        List<Lift> liftList = new ArrayList<>();
        liftRepository.findAll().forEach(l -> liftList.add(l));
        return liftList;
    }
}
