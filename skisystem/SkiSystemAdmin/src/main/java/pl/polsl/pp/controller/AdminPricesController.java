package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.Price;
import pl.polsl.pp.model.TicketCategory;
import pl.polsl.pp.model.TicketType;
import pl.polsl.pp.service.interfaces.IPriceService;
import pl.polsl.pp.service.interfaces.ITicketCategoryService;
import pl.polsl.pp.service.interfaces.ITicketTypeService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/prices")
public class AdminPricesController {

    @Autowired
    @Qualifier("priceServiceInterface")
    private IPriceService priceService;

    @Autowired
    @Qualifier("ticketCategoryServiceInterface")
    private ITicketCategoryService ticketCategoryService;

    @Autowired
    @Qualifier("ticketTypeServiceInterface")
    private ITicketTypeService ticketTypeService;

    @GetMapping("")
    public String showPrices(Model model) {
        model.addAttribute("prices", priceService.getAllPrices()); // DONE: TODO: implement
        return "cms/price/list";
    }

    @GetMapping("/add")
    public String showPricesAdd(Model model) {
        model.addAttribute("price", new Price());
        model.addAttribute("ticketCategories", ticketCategoryService.getAllTicketCategories()); // DONE: TODO: implement
        model.addAttribute("ticketTypes", ticketTypeService.getAllTicketTypes()); // DONE: TODO: implement
        return "cms/price/edit";
    }

    @GetMapping("/edit/{id}")
    public String showPricesEdit(Model model, @PathVariable Long id) {
        model.addAttribute("price", priceService.getPriceById(id)); // DONE: TODO: get the ticket (edit by Pawel: you mean price?)
        model.addAttribute("ticketCategories", ticketCategoryService.getAllTicketCategories()); // DONE TODO: implement
        model.addAttribute("ticketTypes", ticketTypeService.getAllTicketTypes()); // DONE: TODO: implement
        return "cms/price/edit";
    }

    @PostMapping("/submit")
    public String submitPrice(@ModelAttribute("price") Price priceRequest, Model model, final RedirectAttributes redirectAttributes) {
        // DONE: TODO: implement
        Long typeId = priceRequest.getTicketType().getId();
        Long categoryId = priceRequest.getTicketCategory().getId();
        Boolean isSeason = priceRequest.getIsSeason();
        Price price = priceService.getPriceByTypeAndCategoryAndSeason(typeId, categoryId, isSeason);
        if(price != null && priceRequest.getId() == null)
        {
                model.addAttribute("alertText", "Taka pozycja w cenniku już istnieje.");
                model.addAttribute("alertType", "danger");
                model.addAttribute("ticketCategories", ticketCategoryService.getAllTicketCategories()); // DONE: TODO: implement
                model.addAttribute("ticketTypes", ticketTypeService.getAllTicketTypes()); // DONE: TODO: implement
                return "cms/price/edit";
        }

        priceService.savePrice(priceRequest);

        return "redirect:/admin/prices/edit/" + priceRequest.getId();
    }

    @GetMapping("/update")
    public String showPricesUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        // DONE: TODO: implement
        int size = ids.size();

        switch (action) {
            case "delete":
                priceService.deletePrices(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Usunięto cenę." : ("Usunięto " + size + " ceny."));
                break;
        }
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/admin/prices";
    }
}
