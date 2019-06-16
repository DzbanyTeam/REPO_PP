package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Season;
import pl.polsl.pp.repository.SeasonRepository;
import pl.polsl.pp.service.interfaces.ISeasonService;

import java.util.ArrayList;
import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu Season
 */
public class SeasonService implements ISeasonService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli seasons
     */
    @Autowired
    @Qualifier("seasonRepository")
    private SeasonRepository seasonRepository;

    /**
     * Zwraca obiekt Season z danym id
     * @param id Season id
     * @return Season
     */
    @Override
    public Season getSeasonById(Long id) {
        return seasonRepository.findById(id).get();
    }

    /**
     * Dodaje dany obiekt Season do bazy danych
     * @param season obiekt Season do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean saveSeason(Season season) {
        try{
            seasonRepository.save(season);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Usuwa obiekty Season z podanymi id z bazy danych
     * @param ids lista Season id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteSeasons(List<Long> ids) {
        try{
            ids.forEach(id -> seasonRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Aktywuje obiekty Season z podanym id
     * @param ids lista Season id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean activateSeasons(List<Long> ids) {
        return changeActiveStatus(ids, true);
    }

    /**
     * Dezaktywuje obiekty Season z podanym id
     * @param ids lista Season id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deactivateSeasons(List<Long> ids) {
        return changeActiveStatus(ids, false);
    }

    /**
     * Aktywuje/dezaktywuje obiekty Season z podanym id
     * @param ids lista Season id
     * @param newStatus true jeśli obiekty mają być aktywowane, false jeśli mają być dezaktywowane
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @SuppressWarnings("Duplicates")
    private boolean changeActiveStatus(List<Long> ids, boolean newStatus){
        try{
            List<Season> seasonList = (List<Season>)seasonRepository.findAllById(ids);
            seasonList.forEach(s -> {
                s.setIsActive(newStatus);
            });
            seasonRepository.saveAll(seasonList);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów Season
     * @return lista Season
     */
    @Override
    public List<Season> getAllSeasons() {
        List<Season> seasonList = new ArrayList<>();
        seasonRepository.findAll().forEach(s -> seasonList.add(s));
        return seasonList;
    }

    /**
     * Zwraca listę wszystkich obiektów Season, które są aktywne
     * @return lista Season
     */
    @Override
    public List<Season> getAllActiveSeasons() {
        List<Season> activeSeasonList = new ArrayList<>();
        seasonRepository.findAll().forEach(s -> {
            if(s.getIsActive()){
                activeSeasonList.add(s);
            }
        });
        return activeSeasonList;
    }
}
