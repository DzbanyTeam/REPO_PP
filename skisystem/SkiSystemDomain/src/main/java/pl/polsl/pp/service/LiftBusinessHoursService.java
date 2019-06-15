package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.LiftBusinessHours;
import pl.polsl.pp.repository.LiftBusinessHoursRepository;
import pl.polsl.pp.service.interfaces.ILiftBusinessHoursService;

import java.util.ArrayList;
import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu DayOfTheWeek
 */
public class LiftBusinessHoursService implements ILiftBusinessHoursService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli lift_business_hours
     */
    @Autowired
    @Qualifier("liftBusinessHoursRepository")
    private LiftBusinessHoursRepository liftBusinessHoursRepository;

    /**
     * Zwraca obiekt LiftBusinessHours z danym id
     * @param id LiftBusinessHours id
     * @return LiftBusinessHours
     */
    @Override
    public LiftBusinessHours getLiftBusinessHoursById(Long id) {
        return liftBusinessHoursRepository.findById(id).get();
    }

    /**
     * Dodaje dany obiekt LiftBusinessHours do bazy danych
     * @param liftBusinessHours obiekt LiftBusinessHours do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean saveLiftBusinessHours(LiftBusinessHours liftBusinessHours) {
        try{
            liftBusinessHoursRepository.save(liftBusinessHours);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Usuwa obiekty LiftBusinessHours z podanymi id z bazy danych
     * @param ids lista LiftBusinessHours id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteLiftBusinessHours(List<Long> ids) {
        try{
            ids.forEach(id -> liftBusinessHoursRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów LiftBusinessHours
     * @return lista LiftBusinessHours
     */
    @Override
    public List<LiftBusinessHours> getAllLiftBusinessHours() {
        List<LiftBusinessHours> liftBusinessHoursList = new ArrayList<>();
        liftBusinessHoursRepository.findAll().forEach(bh -> liftBusinessHoursList.add(bh));
        return liftBusinessHoursList;
    }


    /**
     * Usuwa obiekty LiftBusinessHours z podanymi id wyciągu i dnia tygodnia z bazy danych
     * @param liftId Lift id
     * @param dayId DayOfTheWeek id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteLiftBusinnesHoursBySlopeIdAndDayId(Long liftId, Long dayId) {
        List<LiftBusinessHours> slopeBusinessHoursList = (List<LiftBusinessHours>)liftBusinessHoursRepository.findAll();

        for(LiftBusinessHours slopeBusinessHours : slopeBusinessHoursList){
            if(slopeBusinessHours.getLift().getId().equals(liftId) && slopeBusinessHours.getDayOfTheWeek().getId().equals(dayId)){
                liftBusinessHoursRepository.deleteById(slopeBusinessHours.getId());
            }
        }
        return true;
    }
}
