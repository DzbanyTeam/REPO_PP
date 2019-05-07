package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Slope;
import pl.polsl.pp.repository.SlopeRepository;
import pl.polsl.pp.service.interfaces.ISlopeService;

import java.util.ArrayList;
import java.util.List;

public class SlopeService implements ISlopeService {

    @Autowired
    @Qualifier("slopeRepository")
    private SlopeRepository slopeRepository;

    @Override
    public Slope getSlopeById(Long id) {
        return slopeRepository.findById(id).get();
    }

    @Override
    public boolean saveSlope(Slope slope) {
        try{
            slopeRepository.save(slope);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteSlopes(List<Long> ids) {
        try{
            ids.forEach(id -> slopeRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean activateSlopes(List<Long> ids) {
        return changeActiveStatus(ids, true);
    }

    @Override
    public boolean deactivateSlopes(List<Long> ids) {
        return changeActiveStatus(ids, false);
    }

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

    @Override
    public List<Slope> getAllSlopes() {
        List<Slope> slopeList = new ArrayList<>();
        slopeRepository.findAll().forEach(s -> slopeList.add(s));
        return slopeList;
    }
}
