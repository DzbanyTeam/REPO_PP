package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.Slope;

import java.util.List;

public interface ISlopeService {

    Slope getSlopeById(Long id);
    boolean saveSlope(Slope slope);
    boolean deleteSlopes(List<Long> ids);
    boolean activateSlopes(List<Long> ids);
    boolean deactivateSlopes(List<Long> ids);
    List<Slope> getAllSlopes();
}
