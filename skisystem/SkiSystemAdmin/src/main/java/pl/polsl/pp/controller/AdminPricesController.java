package pl.polsl.pp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.Price;
import pl.polsl.pp.model.TicketCategory;
import pl.polsl.pp.model.TicketType;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/prices")
public class AdminPricesController {

    @GetMapping("")
    public String showPrices(Model model) {
        model.addAttribute("prices", new ArrayList<Price>()); // TODO: implement
        return "cms/price/list";
    }

    @GetMapping("/add")
    public String showPricesAdd(Model model) {
        Price ticket = new Price();
        model.addAttribute("ticketCategories", new ArrayList<TicketCategory>()); // TODO: implement
        model.addAttribute("ticketTypes", new ArrayList<TicketType>()); // TODO: implement
        return "cms/price/edit";
    }

    @GetMapping("/edit/{id}")
    public String showPricesEdit(Model model, @PathVariable Long id) {
        model.addAttribute("price", new Price()); // TODO: get the ticket
        model.addAttribute("ticketCategories", new ArrayList<TicketCategory>()); // TODO: implement
        model.addAttribute("ticketTypes", new ArrayList<TicketType>()); // TODO: implement
        return "cms/price/edit";
    }

    @PostMapping("/submit")
    public String submitPrice(@ModelAttribute("price") @Validated Price priceRequest, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
        // TODO: implement

        return "redirect:/admin/prices/edit/" + priceRequest.getId();
    }

    @GetMapping("/update")
    public String showPricesUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        // TODO: implement

        return "redirect:/admin/prices";
    }
}
