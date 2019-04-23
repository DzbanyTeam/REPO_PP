package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.TicketType;

import java.util.List;

public interface ITicketTypeService {

    TicketType getTicketTypeById(Long id);
    boolean saveTicketType(TicketType ticketType);
    boolean deleteTicketTypes(List<Long> ids);
    boolean activateTicketTypes(List<Long> ids);
    boolean deactivateTicketTypes(List<Long> ids);
    List<TicketType> getAllTicketTypes();
}
