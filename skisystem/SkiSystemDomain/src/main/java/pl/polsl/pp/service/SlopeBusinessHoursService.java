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
