package pl.polsl.pp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.TicketType;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/ticket-types")
public class AdminTicketTypesController {

    @GetMapping("")
    public String showTicketTypes(Model model) {
        model.addAttribute("ticketTypes", new ArrayList<TicketType>()); // TODO: implement
        return "cms/ticketType/list";
    }

    @GetMapping("/add")
    public String showTicketTypesAdd(Model model) {
        TicketType ticket = new TicketType();
        return "cms/ticketType/edit";
    }

    @GetMapping("/edit/{id}")
    public String showTicketTypesEdit(Model model, @PathVariable Long id) {
        model.addAttribute("ticketType", new TicketType()); // TODO: get the ticket
        return "cms/ticketType/edit";
    }

    @PostMapping("/submit")
    public String submitTicketType(@ModelAttribute("ticketType") @Validated TicketType ticketTypeRequest, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
        // TODO: implement

        return "redirect:/admin/ticket-types/edit/" + ticketTypeRequest.getId();
    }

    @GetMapping("/update")
    public String showTicketTypesUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        // TODO: implement

        return "redirect:/admin/ticket-types";
    }
}
