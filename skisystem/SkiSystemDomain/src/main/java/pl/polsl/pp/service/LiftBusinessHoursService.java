package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.LiftBusinessHours;
import pl.polsl.pp.repository.LiftBusinessHoursRepository;
import pl.polsl.pp.service.interfaces.ILiftBusinessHoursService;

import java.util.ArrayList;
import java.util.List;

public class LiftBusinessHoursService implements ILiftBusinessHoursService {

    @Autowired
    @Qualifier("liftBusinessHoursRepository")
    private LiftBusinessHoursRepository liftBusinessHoursRepository;

    @Override
    public LiftBusinessHours getLiftBusinessHoursById(Long id) {
        return liftBusinessHoursRepository.findById(id).get();
    }

    @Override
    public boolean saveLiftBusinessHours(LiftBusinessHours liftBusinessHours) {
        try{
            liftBusinessHoursRepository.save(liftBusinessHours);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteLiftBusinessHours(List<Long> ids) {
        try{
            ids.forEach(id -> liftBusinessHoursRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<LiftBusinessHours> getAllLiftBusinessHours() {
        List<LiftBusinessHours> liftBusinessHoursList = new ArrayList<>();
        liftBusinessHoursRepository.findAll().forEach(bh -> liftBusinessHoursList.add(bh));
        return liftBusinessHoursList;
    }
}
