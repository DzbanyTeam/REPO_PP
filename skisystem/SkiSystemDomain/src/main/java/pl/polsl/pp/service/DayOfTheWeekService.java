package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.DayOfTheWeek;
import pl.polsl.pp.repository.DayOfTheWeekRepository;
import pl.polsl.pp.service.interfaces.IDayOfTheWeekService;

import java.util.ArrayList;
import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu DayOfTheWeek
 */
public class DayOfTheWeekService implements IDayOfTheWeekService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli days_of_the_week
     */
    @Autowired
    @Qualifier("dayOfTheWeekRepository")
    private DayOfTheWeekRepository dayOfTheWeekRepository;

    /**
     * Zwraca obiekt DayOfTheWeek z danym id
     * @param id DayOfTheWeek id
     * @return DayOfTheWeek
     */
    @Override
    public DayOfTheWeek getDayOfTheWeekById(Long id) {
        return dayOfTheWeekRepository.findById(id).get();
    }

    /**
     * Dodaje dany obiekt DayOfTheWeek do bazy danych
     * @param dayOfTheWeek obiekt DayOfTheWeek do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean saveDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
        try{ 
            dayOfTheWeekRepository.save(dayOfTheWeek);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Usuwa obiekty DayOfTheWeek z podanymi id z bazy danych
     * @param ids lista DayOfTheWeek id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteDaysOfTheWeek(List<Long> ids) {
        try{
            ids.forEach(id -> dayOfTheWeekRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów DayOfTheWeek
     * @return lista DayOfTheWeek
     */
    @Override
    public List<DayOfTheWeek> getAllDaysOfTheWeek() {
        List<DayOfTheWeek> dayOfTheWeekList = new ArrayList<>();
        dayOfTheWeekRepository.findAll().forEach(d -> dayOfTheWeekList.add(d));
        return dayOfTheWeekList;
    }
}
