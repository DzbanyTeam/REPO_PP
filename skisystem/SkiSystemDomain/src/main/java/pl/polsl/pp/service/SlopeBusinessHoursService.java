package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Slope;
import pl.polsl.pp.model.SlopeBusinessHours;
import pl.polsl.pp.repository.SlopeBusinessHoursRepository;
import pl.polsl.pp.service.interfaces.ISlopeBusinessHoursService;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 * Usługa wykonująca operacje na obiektach typu SlopeBusinessHours
 */
public class SlopeBusinessHoursService implements ISlopeBusinessHoursService {

    /**
     * Repozytorium pozwalające na aktualizowanie tabeli slope_business_hours
     */
    @Autowired
    @Qualifier("slopeBusinessHoursRepository")
    private SlopeBusinessHoursRepository slopeBusinessHoursRepository;

    /**
     * Zwraca obiekt SlopeBusinessHours z danym id
     * @param id SlopeBusinessHours id
     * @return SlopeBusinessHours
     */
    @Override
    public SlopeBusinessHours getSlopeBusinessHoursById(Long id) {
        return slopeBusinessHoursRepository.findById(id).get();
    }

    /**
     * Dodaje dany obiekt SlopeBusinessHours do bazy danych
     * @param slopeBusinessHours obiekt SlopeBusinessHours do zapisu
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean saveSlopeBusinessHours(SlopeBusinessHours slopeBusinessHours) {
        try{
            slopeBusinessHoursRepository.save(slopeBusinessHours);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    /**
     * Usuwa obiekty SlopeBusinessHours z podanymi id z bazy danych
     * @param ids lista SlopeBusinessHours id
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean deleteSlopeBusinessHours(List<Long> ids) {
        try{
            ids.forEach(id -> slopeBusinessHoursRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    /**
     * Zwraca listę wszystkich obiektów SlopeBusinessHours
     * @return lista SlopeBusinessHours
     */
    @Override
    public List<SlopeBusinessHours> getAllSlopeBusinessHours() {
        List<SlopeBusinessHours> slopeBusinessHoursList = new ArrayList<>();
        slopeBusinessHoursRepository.findAll().forEach(bh -> slopeBusinessHoursList.add(bh));
        return slopeBusinessHoursList;
    }

    /**
     * Aktualizuje godzinę otwarcia i zamknięcia obiektom SlopeBusinessHours o podanym id stoku i dnia tygodnia
     * @param slopeId Slope id
     * @param dayId DayOfTheWeek id
     * @param openingHour nowa godzina otwarcia
     * @param closingHour nowa godzina zamknięcia
     * @return true jeśli operacja powiodłą się, false jeśli nie powiodła się
     */
    @Override
    public boolean updateSlopeBusinnesHoursBySlopeIdAndDayId(Long slopeId, Long dayId, Time openingHour, Time closingHour) {
        List<SlopeBusinessHours> slopeBusinessHoursList = (List<SlopeBusinessHours>)slopeBusinessHoursRepository.findAll();
            for (SlopeBusinessHours sb: slopeBusinessHoursList){
                if(sb.getSlope().getId().equals(slopeId) && sb.getDayOfTheWeek().getId().equals(dayId)){
                    sb.setOpeningHour(openingHour);
                    sb.setClosingHour(closingHour);
                    slopeBusinessHoursRepository.save(sb);
                    return true;
                }
            }
            return false;
    }

    /**
     * Usuwa obiekty SlopeBusinessHours z podanymi id stoku i dnia tygodnia z bazy danych
     * @param slopeId Slope id
     * @param dayId DayOfTheWeek id
     * @return true jeśli operacja powiodłą się
     */
    @Override
    public boolean deleteSlopeBusinnesHoursBySlopeIdAndDayId(Long slopeId, Long dayId){

        List<SlopeBusinessHours> slopeBusinessHoursList = (List<SlopeBusinessHours>)slopeBusinessHoursRepository.findAll();

        for(SlopeBusinessHours slopeBusinessHours : slopeBusinessHoursList){
            if(slopeBusinessHours.getSlope().getId().equals(slopeId) && slopeBusinessHours.getDayOfTheWeek().getId().equals(dayId)){
                slopeBusinessHoursRepository.deleteById(slopeBusinessHours.getId());
            }
        }
        return true;
    }
}
