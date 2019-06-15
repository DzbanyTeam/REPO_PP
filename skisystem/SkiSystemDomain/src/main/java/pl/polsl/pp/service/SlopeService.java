package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Slope;
import pl.polsl.pp.repository.SlopeRepository;
import pl.polsl.pp.service.interfaces.ISlopeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu Slope
 */
public class SlopeService implements ISlopeService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli slopes
     */
    @Autowired
    @Qualifier("slopeRepository")
    private SlopeRepository slopeRepository;

    /**
     * Zwraca obiekt Slope z danym id
     * @param id Slope id
     * @return Slope
     */
    @Override
    public Slope getSlopeById(Long id) {
        return slopeRepository.findById(id).get();
    }

    /**
     * Dodaje dany obiekt Slope do bazy danych
     * @param slope obiekt Slope do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean saveSlope(Slope slope) {
        try{
            slopeRepository.save(slope);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Usuwa obiekty Slope z podanymi id z bazy danych
     * @param ids lista Slope id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteSlopes(List<Long> ids) {
        try{
            ids.forEach(id -> slopeRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Aktywuje obiekty Slope z podanym id
     * @param ids lista Slope id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean activateSlopes(List<Long> ids) {
        return changeActiveStatus(ids, true);
    }

    /**
     * Dezaktywuje obiekty Slope z podanym id
     * @param ids lista Slope id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deactivateSlopes(List<Long> ids) {
        return changeActiveStatus(ids, false);
    }

    /**
     * Aktywuje/dezaktywuje obiekty Slope z podanym id
     * @param ids lista Slope id
     * @param newStatus true jeśli obiekty mają być aktywowane, false jeśli mają być dezaktywowane
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @SuppressWarnings("Duplicates")
    private boolean changeActiveStatus(List<Long> ids, boolean newStatus){
        try{
            List<Slope> slopeList = (List<Slope>)slopeRepository.findAllById(ids);
            slopeList.forEach(s -> {
                s.setIsActive(newStatus);
            });
            slopeRepository.saveAll(slopeList);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów Slope
     * @return lista Slope
     */
    @Override
    public List<Slope> getAllSlopes() {
        List<Slope> slopeList = new ArrayList<>();
        slopeRepository.findAll().forEach(s -> slopeList.add(s));
        return slopeList;
    }
}
