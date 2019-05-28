package pl.polsl.pp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.TicketCategory;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/ticket-categories")
public class AdminTicketCategoriesController {

    @GetMapping("")
    public String showTicketCategories(Model model) {
        model.addAttribute("ticketCategories", new ArrayList<TicketCategory>()); // TODO: implement
        return "cms/ticketCategory/list";
    }

    @GetMapping("/add")
    public String showTicketCategoriesAdd(Model model) {
        model.addAttribute("ticketCategory", new TicketCategory());
        return "cms/ticketCategory/edit";
    }

    @GetMapping("/edit/{id}")
    public String showTicketCategoriesEdit(Model model, @PathVariable Long id) {
        model.addAttribute("ticketCategory", new TicketCategory()); // TODO: get the ticket
        return "cms/ticketCategory/edit";
    }

    @PostMapping("/submit")
    public String submitTicketCategory(@ModelAttribute("ticketCategory") @Validated TicketCategory ticketCategoryRequest, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
        // TODO: implement

        return "redirect:/admin/ticket-categories/edit/" + ticketCategoryRequest.getId();
    }

    @GetMapping("/update")
    public String showTicketCategoriesUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        // TODO: implement

        return "redirect:/admin/ticket-categories";
    }
}
