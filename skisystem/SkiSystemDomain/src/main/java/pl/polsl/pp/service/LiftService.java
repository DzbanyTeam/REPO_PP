package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Lift;
import pl.polsl.pp.repository.LiftRepository;
import pl.polsl.pp.service.interfaces.ILiftService;

import java.util.ArrayList;
import java.util.List;

public class LiftService implements ILiftService {

    @Autowired
    @Qualifier("liftRepository")
    private LiftRepository liftRepository;

    @Override
    public Lift getLiftById(Long id) {
        return liftRepository.findById(id).get();
    }

    @Override
    public boolean saveLift(Lift lift) {
        try{
            liftRepository.save(lift);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteLifts(List<Long> ids) {
        try{
            ids.forEach(id -> liftRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean activateLifts(List<Long> ids) {
        return changeActiveStatus(ids, true);
    }

    @Override
    public boolean deactivateLifts(List<Long> ids) {
        return changeActiveStatus(ids, false);
    }

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

    @Override
    public List<Lift> getAllLifts() {
        List<Lift> liftList = new ArrayList<>();
        liftRepository.findAll().forEach(l -> liftList.add(l));
        return liftList;
    }
}
