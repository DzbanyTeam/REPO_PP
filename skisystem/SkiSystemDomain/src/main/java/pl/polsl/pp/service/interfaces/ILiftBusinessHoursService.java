package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.LiftBusinessHours;

import java.util.List;

public interface ILiftBusinessHoursService {

    LiftBusinessHours getLiftBusinessHoursById(Long id);
    boolean saveLiftBusinessHours(LiftBusinessHours liftBusinessHours);
    boolean deleteLiftBusinessHours(List<Long> ids);
    List<LiftBusinessHours> getAllLiftBusinessHours();
    boolean deleteLiftBusinnesHoursBySlopeIdAndDayId(Long slopeId, Long dayId);
}
