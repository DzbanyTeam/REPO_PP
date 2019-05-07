package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.TicketUse;

import java.util.List;

public interface ITicketUseService {

    TicketUse getTicketUseById(Long id);
    boolean saveTicketUse(TicketUse ticketUse);
    boolean deleteTicketUses(List<Long> ids);
    List<TicketUse> getAllTicketUses();
}
