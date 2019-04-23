package pl.polsl.pp.service.interfaces;

import pl.polsl.pp.model.TicketCategory;

import java.util.List;

public interface ITicketCategoryService {

    TicketCategory getTicketCategoryById(Long id);
    boolean saveTicketCategory(TicketCategory ticketCategory);
    boolean deleteTicketCategories(List<Long> ids);
    List<TicketCategory> getAllTicketCategories();
}
