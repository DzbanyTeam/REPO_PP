package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.TicketType;
import pl.polsl.pp.service.interfaces.ITicketTypeService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/ticket-types")
public class AdminTicketTypesController {

    @Autowired
    @Qualifier("ticketTypeServiceInterface")
    private ITicketTypeService ticketTypeService;

    @GetMapping("")
    public String showTicketTypes(Model model) {
        model.addAttribute("ticketTypes", ticketTypeService.getAllTicketTypes()); // DONE: TODO: implement
        return "cms/ticketType/list";
    }

    @GetMapping("/add")
    public String showTicketTypesAdd(Model model) {
        model.addAttribute("ticketType", new TicketType());
        return "cms/ticketType/edit";
    }

    @GetMapping("/edit/{id}")
    public String showTicketTypesEdit(Model model, @PathVariable Long id) {
        model.addAttribute("ticketType", ticketTypeService.getTicketTypeById(id)); // DONE: TODO: get the ticket
        return "cms/ticketType/edit";
    }

    @PostMapping("/submit")
    public String submitTicketType(@ModelAttribute("ticketType") TicketType ticketTypeRequest, Model model, final RedirectAttributes redirectAttributes) {
        // DONE: TODO: implement

        ticketTypeService.saveTicketType(ticketTypeRequest);

        redirectAttributes.addFlashAttribute("alertText", "Zapisano typ.");
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/ticket-types/edit/" + ticketTypeRequest.getId();
    }

    @GetMapping("/update")
    public String showTicketTypesUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        // DONE: TODO: implement

        int size = ids.size();
        switch (action) {
            case "delete":
                ticketTypeService.deleteTicketTypes(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Usunięto rodzaj biletu." : ("Usunięto " + size + " rodzaje biletu."));
                break;
            case "activate":
                ticketTypeService.activateTicketTypes(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Aktywowano rodzaj biletu." : ("Aktywowano " + size + " rodzaje biletu."));
                break;
            case "deactivate":
                ticketTypeService.deactivateTicketTypes(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Dezaktywowano rodzaj biletu." : ("Dezaktywowano " + size + " rodzaje biletu."));
                break;
        }
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/admin/ticket-types";
    }
}
