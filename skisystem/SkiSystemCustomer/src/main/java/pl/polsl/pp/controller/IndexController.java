package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.polsl.pp.model.*;
import pl.polsl.pp.service.interfaces.ICustomerAccountService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("")
public class IndexController {

    @Autowired
    @Qualifier("customerAccountService")
    private ICustomerAccountService customerAccountService;

    @GetMapping("/")
    public String showIndexPage(){
        return "site/home/index";
    }

    @GetMapping("/prices")
    public String showPrices(Model model) {
        model.addAttribute("ticketTypes", new ArrayList<TicketType>()); // TODO: implement
        model.addAttribute("ticketCategories", new ArrayList<TicketCategory>()); // TODO: implement
        model.addAttribute("prices", new HashMap<Long, Map<Long, Price>>()); // TODO: implement
        return "site/ski/prices";
    }

    @GetMapping("/lifts")
    public String showLifts(Model model) {
        model.addAttribute("lifts", new ArrayList<Lift>()); // TODO: implement
        return "site/ski/lifts";
    }

    @GetMapping("/slopes")
    public String showSlopes(Model model) {
        model.addAttribute("slopes", new ArrayList<Slope>()); // TODO: implement
        return "site/ski/slopes";
    }

}
