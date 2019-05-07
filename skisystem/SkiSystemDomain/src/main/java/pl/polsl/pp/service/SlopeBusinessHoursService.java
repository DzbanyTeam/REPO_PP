package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.SlopeBusinessHours;
import pl.polsl.pp.repository.SlopeBusinessHoursRepository;
import pl.polsl.pp.service.interfaces.ISlopeBusinessHoursService;

import java.util.ArrayList;
import java.util.List;

public class SlopeBusinessHoursService implements ISlopeBusinessHoursService {

    @Autowired
    @Qualifier("slopeBusinessHoursRepository")
    private SlopeBusinessHoursRepository slopeBusinessHoursRepository;

    @Override
    public SlopeBusinessHours getSlopeBusinessHoursById(Long id) {
        return slopeBusinessHoursRepository.findById(id).get();
    }

    @Override
    public boolean saveSlopeBusinessHours(SlopeBusinessHours slopeBusinessHours) {
        try{
            slopeBusinessHoursRepository.save(slopeBusinessHours);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteSlopeBusinessHours(List<Long> ids) {
        try{
            ids.forEach(id -> slopeBusinessHoursRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<SlopeBusinessHours> getAllSlopeBusinessHours() {
        List<SlopeBusinessHours> slopeBusinessHoursList = new ArrayList<>();
        slopeBusinessHoursRepository.findAll().forEach(bh -> slopeBusinessHoursList.add(bh));
        return slopeBusinessHoursList;
    }
}
