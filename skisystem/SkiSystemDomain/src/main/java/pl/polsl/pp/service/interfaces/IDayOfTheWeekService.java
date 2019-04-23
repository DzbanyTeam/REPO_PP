package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.DayOfTheWeek;

import java.util.List;

public interface IDayOfTheWeekService {

    DayOfTheWeek getDayOfTheWeekById(Long id);
    boolean saveDayOfTheWeek(DayOfTheWeek dayOfTheWeek);
    boolean deleteDaysOfTheWeek(List<Long> ids);
    List<DayOfTheWeek> getAllDaysOfTheWeek();
}
