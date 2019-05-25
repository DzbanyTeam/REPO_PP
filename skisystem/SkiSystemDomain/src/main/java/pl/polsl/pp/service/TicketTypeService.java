package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.TicketType;
import pl.polsl.pp.repository.TicketTypeRepository;
import pl.polsl.pp.service.interfaces.ITicketTypeService;

import java.util.ArrayList;
import java.util.List;

public class TicketTypeService implements ITicketTypeService {

    @Autowired
    @Qualifier("ticketTypeRepository")
    private TicketTypeRepository ticketTypeRepository;

    @Override
    public TicketType getTicketTypeById(Long id) {
        return ticketTypeRepository.findById(id).get();
    }

    @Override
    public boolean saveTicketType(TicketType ticketType) {
        try{
            ticketTypeRepository.save(ticketType);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteTicketTypes(List<Long> ids) {
        try{
            ids.forEach(id -> ticketTypeRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean activateTicketTypes(List<Long> ids) {
        return changeActiveStatus(ids, true);
    }

    @Override
    public boolean deactivateTicketTypes(List<Long> ids) {
        return changeActiveStatus(ids, false);
    }

    @SuppressWarnings("Duplicates")
    private boolean changeActiveStatus(List<Long> ids, boolean newStatus){
        try{
            List<TicketType> ticketTypeList = (List<TicketType>)ticketTypeRepository.findAllById(ids);
            ticketTypeList.forEach(l -> {
                l.setIsActive(newStatus);
            });
            ticketTypeRepository.saveAll(ticketTypeList);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public List<TicketType> getAllTicketTypes() {
        List<TicketType> ticketTypeList = new ArrayList<>();
        ticketTypeRepository.findAll().forEach(t -> ticketTypeList.add(t));
        return ticketTypeList;
    }
}
