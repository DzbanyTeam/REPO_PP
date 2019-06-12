package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.DayOfTheWeek;
import pl.polsl.pp.repository.DayOfTheWeekRepository;
import pl.polsl.pp.service.interfaces.IDayOfTheWeekService;

import java.util.ArrayList;
import java.util.List;

public class DayOfTheWeekService implements IDayOfTheWeekService {

    @Autowired
    @Qualifier("dayOfTheWeekRepository")
    private DayOfTheWeekRepository dayOfTheWeekRepository;

    @Override
    public DayOfTheWeek getDayOfTheWeekById(Long id) {
        return dayOfTheWeekRepository.findById(id).get();
    }

    @Override
    public boolean saveDayOfTheWeek(DayOfTheWeek dayOfTheWeek) {
        try{ 
            dayOfTheWeekRepository.save(dayOfTheWeek);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteDaysOfTheWeek(List<Long> ids) {
        try{
            ids.forEach(id -> dayOfTheWeekRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<DayOfTheWeek> getAllDaysOfTheWeek() {
        List<DayOfTheWeek> dayOfTheWeekList = new ArrayList<>();
        dayOfTheWeekRepository.findAll().forEach(d -> dayOfTheWeekList.add(d));
        return dayOfTheWeekList;
    }
}
