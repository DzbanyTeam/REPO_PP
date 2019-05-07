package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.Lift;

import java.util.List;
import java.util.Map;

public interface ILiftService {

    Lift getLiftById(Long id);
    boolean saveLift(Lift lift);
    boolean deleteLifts(List<Long> ids);
    boolean activateLifts(List<Long> ids);
    boolean deactivateLifts(List<Long> ids);
    List<Lift> getAllLifts();
}
