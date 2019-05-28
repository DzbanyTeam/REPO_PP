package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.TicketCategory;
import pl.polsl.pp.service.interfaces.ITicketCategoryService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/ticket-categories")
public class AdminTicketCategoriesController {

    @Autowired
    @Qualifier("ticketCategoryServiceInterface")
    private ITicketCategoryService ticketCategoryService;

    @GetMapping("")
    public String showTicketCategories(Model model) {
        model.addAttribute("ticketCategories", ticketCategoryService.getAllTicketCategories()); // DONE: TODO: implement
        return "cms/ticketCategory/list";
    }

    @GetMapping("/add")
    public String showTicketCategoriesAdd(Model model) {
        model.addAttribute("ticketCategory", new TicketCategory());
        return "cms/ticketCategory/edit";
    }

    @GetMapping("/edit/{id}")
    public String showTicketCategoriesEdit(Model model, @PathVariable Long id) {
        model.addAttribute("ticketCategory", ticketCategoryService.getTicketCategoryById(id)); // DONE: TODO: get the ticket
        return "cms/ticketCategory/edit";
    }

    @PostMapping("/submit")
    public String submitTicketCategory(@ModelAttribute("ticketCategory") TicketCategory ticketCategoryRequest, Model model, final RedirectAttributes redirectAttributes) {
        // DONE: TODO: implement
        ticketCategoryService.saveTicketCategory(ticketCategoryRequest);

        return "redirect:/admin/ticket-categories/edit/" + ticketCategoryRequest.getId();
    }

    @GetMapping("/update")
    public String showTicketCategoriesUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        // DONE: TODO: implement

        int size = ids.size();
        switch (action) {
            case "delete":
                ticketCategoryService.deleteTicketCategories(ids);

                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Usunięto kategorię biletu." : ("Usunięto " + size + " kategorie biletu."));
                break;
        }
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/ticket-categories";
    }
}
