package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.SlopeBusinessHours;

import java.sql.Time;
import java.util.List;

public interface ISlopeBusinessHoursService {

    SlopeBusinessHours getSlopeBusinessHoursById(Long id);
    boolean saveSlopeBusinessHours(SlopeBusinessHours slopeBusinessHours);
    boolean deleteSlopeBusinessHours(List<Long> ids);
    List<SlopeBusinessHours> getAllSlopeBusinessHours();
    boolean updateSlopeBusinnesHoursBySlopeIdAndDayId(Long slopeId, Long dayId, Time openingHour, java.sql.Time closingHour);
    boolean deleteSlopeBusinnesHoursBySlopeIdAndDayId(Long slopeId, Long dayId);
}
