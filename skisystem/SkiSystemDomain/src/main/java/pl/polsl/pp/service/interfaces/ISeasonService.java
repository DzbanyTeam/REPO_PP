package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.Season;

import java.util.List;

public interface ISeasonService {

    Season getSeasonById(Long id);
    boolean saveSeason(Season ticketCategory);
    boolean deleteSeasons(List<Long> ids);
    boolean activateSeasons(List<Long> ids);
    boolean deactivateSeasons(List<Long> ids);
    List<Season> getAllSeasons();
    List<Season> getAllActiveSeasons();
}
