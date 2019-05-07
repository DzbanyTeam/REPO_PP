package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.TicketUse;
import pl.polsl.pp.repository.TicketUseRepository;
import pl.polsl.pp.service.interfaces.ITicketUseService;

import java.util.ArrayList;
import java.util.List;

public class TicketUseService implements ITicketUseService {

    @Autowired
    @Qualifier("ticketUseRepository")
    private TicketUseRepository ticketUseRepository;

    @Override
    public TicketUse getTicketUseById(Long id) {
        return ticketUseRepository.findById(id).get();
    }

    @Override
    public boolean saveTicketUse(TicketUse ticketUse) {
        try{
            ticketUseRepository.save(ticketUse);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteTicketUses(List<Long> ids) {
        try{
            ids.forEach(id -> ticketUseRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<TicketUse> getAllTicketUses() {
        List<TicketUse> ticketUseList = new ArrayList<>();
        ticketUseRepository.findAll().forEach(t -> ticketUseList.add(t));
        return ticketUseList;
    }
}
