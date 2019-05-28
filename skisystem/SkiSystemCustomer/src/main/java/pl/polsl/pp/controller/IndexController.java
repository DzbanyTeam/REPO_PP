package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.polsl.pp.model.*;
import pl.polsl.pp.service.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("")
public class IndexController {

    @Autowired
    @Qualifier("priceServiceInterface")
    private IPriceService priceService;

    @Autowired
    @Qualifier("customerAccountServiceInterface")
    private ICustomerAccountService customerAccountService;

    @Autowired
    @Qualifier("ticketCategoryServiceInterface")
    private ITicketCategoryService ticketCategoryService;

    @Autowired
    @Qualifier("ticketTypeServiceInterface")
    private ITicketTypeService ticketTypeService;

    @Autowired
    @Qualifier("liftServiceInterface")
    private ILiftService liftService;

    @Autowired
    @Qualifier("slopeServiceInterface")
    private ISlopeService slopeService;

    @GetMapping("/")
    public String showIndexPage(){
        return "site/home/index";
    }

    @GetMapping("/prices")
    public String showPrices(Model model) {
        model.addAttribute("ticketTypes", ticketTypeService.getAllTicketTypes()); // DONE: TODO: implement
        model.addAttribute("ticketCategories", ticketCategoryService.getAllTicketCategories()); // DONE: TODO: implement


        // zwraca poprawne ceny. zbebuggowane. Ale za to front nie wyświetla tych cen
        HashMap<TicketCategory, Map<TicketType, Price>> categoryTypesPricesMap = new HashMap<>();
        for(TicketCategory category: ticketCategoryService.getAllTicketCategories()) {
            Map<TicketType, Price> typePriceMap = new HashMap<>();
            for (TicketType type : ticketTypeService.getAllTicketTypes()) {
                Price price = priceService.getPriceByTypeAndCategory(type.getId(), category.getId());
                typePriceMap.put(type, price);
                categoryTypesPricesMap.put(category, typePriceMap);
            }
        }

        model.addAttribute("prices", categoryTypesPricesMap); // IN PROGRESS: TODO: implement
        return "site/ski/prices";
    }

    @GetMapping("/lifts")
    public String showLifts(Model model) {
        model.addAttribute("lifts", liftService.getAllLifts()); //DONE: TODO: implement
        return "site/ski/lifts";
    }

    @GetMapping("/slopes")
    public String showSlopes(Model model) {
        model.addAttribute("slopes", slopeService.getAllSlopes()); // DONE: TODO: implement
        return "site/ski/slopes";
    }

}
