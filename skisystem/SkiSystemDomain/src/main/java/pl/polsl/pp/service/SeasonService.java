package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.Season;
import pl.polsl.pp.repository.SeasonRepository;
import pl.polsl.pp.service.interfaces.ISeasonService;

import java.util.ArrayList;
import java.util.List;

public class SeasonService implements ISeasonService {

    @Autowired
    @Qualifier("seasonRepository")
    private SeasonRepository seasonRepository;

    @Override
    public Season getSeasonById(Long id) {
        return seasonRepository.findById(id).get();
    }

    @Override
    public boolean saveSeason(Season season) {
        try{
            seasonRepository.save(season);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteSeasons(List<Long> ids) {
        try{
            ids.forEach(id -> seasonRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean activateSeasons(List<Long> ids) {
        return changeActiveStatus(ids, true);
    }

    @Override
    public boolean deactivateSeasons(List<Long> ids) {
        return changeActiveStatus(ids, false);
    }

    @SuppressWarnings("Duplicates")
    private boolean changeActiveStatus(List<Long> ids, boolean newStatus){
        try{
            List<Season> seasonList = (List<Season>)seasonRepository.findAllById(ids);
            seasonList.forEach(s -> {
                s.setIsActive(newStatus);
            });
            seasonRepository.saveAll(seasonList);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<Season> getAllSeasons() {
        List<Season> seasonList = new ArrayList<>();
        seasonRepository.findAll().forEach(s -> seasonList.add(s));
        return seasonList;
    }
}
