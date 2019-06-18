package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.polsl.pp.model.Price;
import pl.polsl.pp.model.Season;
import pl.polsl.pp.model.TicketCategory;
import pl.polsl.pp.model.TicketType;
import pl.polsl.pp.service.interfaces.*;

import java.util.HashMap;
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

    @Autowired
    @Qualifier("seasonServiceInterface")
    private ISeasonService seasonService;

    @GetMapping("/")
    public String showIndexPage() {
        return "site/home/index";
    }

    @GetMapping("/prices")
    public String showPrices(Model model) {
        model.addAttribute("ticketTypes", ticketTypeService.getAllTicketTypes());
        model.addAttribute("ticketCategories", ticketCategoryService.getAllTicketCategories());
        model.addAttribute("seasons", seasonService.getAllActiveSeasons());

        // zwraca ceny w sezonie
        HashMap<Season, Map<TicketCategory, Map<TicketType, Price>>> categoryTypesPricesInSeasonMap = new HashMap<>();

        for (Season season : seasonService.getAllActiveSeasons()) {
            Map<TicketCategory, Map<TicketType, Price>> seasonMap = new HashMap<>();
            for (TicketCategory category : ticketCategoryService.getAllTicketCategories()) {
                Map<TicketType, Price> typePriceMap = new HashMap<>();
                seasonMap.put(category, typePriceMap);
                for (TicketType type : ticketTypeService.getAllTicketTypes()) {
                    Price price = priceService.getPriceByTypeAndCategoryAndSeason(type.getId(), category.getId(), season.getId());
                    typePriceMap.put(type, price);
                    categoryTypesPricesInSeasonMap.put(season, seasonMap);
                }
            }
        }


        model.addAttribute("pricesInSeason", categoryTypesPricesInSeasonMap);
        return "site/ski/prices";
    }

    @GetMapping("/lifts")
    public String showLifts(Model model) {
        model.addAttribute("lifts", liftService.getAllLifts());
        return "site/ski/lifts";
    }

    @GetMapping("/slopes")
    public String showSlopes(Model model) {
        model.addAttribute("slopes", slopeService.getAllSlopes());
        return "site/ski/slopes";
    }

}