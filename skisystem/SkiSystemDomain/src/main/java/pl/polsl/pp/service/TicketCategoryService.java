package pl.polsl.pp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import pl.polsl.pp.model.TicketCategory;
import pl.polsl.pp.repository.TicketCategoryRepository;
import pl.polsl.pp.service.interfaces.ITicketCategoryService;

import java.util.ArrayList;
import java.util.List;

public class TicketCategoryService implements ITicketCategoryService {

    @Autowired
    @Qualifier("ticketCategoryRepository")
    private TicketCategoryRepository ticketCategoryRepository;

    @Override
    public TicketCategory getTicketCategoryById(Long id) {
        return ticketCategoryRepository.findById(id).get();
    }

    @Override
    public boolean saveTicketCategory(TicketCategory ticketCategory) {
        try{
            ticketCategoryRepository.save(ticketCategory);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    @Override
    public boolean deleteTicketCategories(List<Long> ids) {
        try{
            ids.forEach(id -> ticketCategoryRepository.deleteById(id));
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public List<TicketCategory> getAllTicketCategories() {
        List<TicketCategory> ticketCategoryList = new ArrayList<>();
        ticketCategoryRepository.findAll().forEach(t -> ticketCategoryList.add(t));
        return ticketCategoryList;
    }
}
