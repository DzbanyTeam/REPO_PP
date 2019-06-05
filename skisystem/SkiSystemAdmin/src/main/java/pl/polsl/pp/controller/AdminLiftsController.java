package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.*;
import pl.polsl.pp.service.interfaces.IDayOfTheWeekService;
import pl.polsl.pp.service.interfaces.ILiftService;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin/lifts")
public class AdminLiftsController {

    @Autowired
    @Qualifier("liftServiceInterface")
    private ILiftService liftService;

    @Autowired
    @Qualifier("dayOfTheWeekServiceInterface")
    private IDayOfTheWeekService dayOfTheWeekService;

    //@Autowired
    //private LiftValidator liftValidator;

    @InitBinder
    protected void initBinderLift(WebDataBinder binder) {
        //binder.addValidators(liftValidator);
    }

    public AdminLiftsController() {
    }

    @GetMapping("")
    public String showLifts(Model model) {
        model.addAttribute("lifts", liftService.getAllLifts());
        return "cms/lift/list";
    }
    @GetMapping("/edit/{id}")
    public String showLiftsEdit(Model model, @PathVariable Long id) {
        model.addAttribute("lift", liftService.getLiftById(id));
        model.addAttribute("daysOfTheWeek", dayOfTheWeekService.getAllDaysOfTheWeek());
        model.addAttribute("businessHours", new HashMap<DayOfTheWeek, LiftBusinessHours>()); // TODO Get this lift's business hours in a map identified by day of the week
        return "cms/lift/edit";
    }

    @GetMapping("/add")
    public String showSlopesAdd(Model model) {
        model.addAttribute("lift", new Lift());
        model.addAttribute("daysOfTheWeek", dayOfTheWeekService.getAllDaysOfTheWeek());
        model.addAttribute("businessHours", new HashMap<DayOfTheWeek, LiftBusinessHours>());
        return "cms/lift/edit";
    }

    @PostMapping("/submit")
    public String submitCustomer(@ModelAttribute("lift") @Validated Lift liftRequest, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("alertText", "Nie można zapisać wyciągu. Sprawdź błędy formularza.");
            model.addAttribute("alertType", "danger");
            return "cms/lift/edit";
        }

        liftService.saveLift(liftRequest);

        redirectAttributes.addFlashAttribute("alertText", "Zapisano wyciąg.");
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/lifts/edit/" + liftRequest.getId();
    }

    @GetMapping("/update")
    public String showLiftsUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        int size = ids.size();

        switch (action) {
            case "delete":
                liftService.deleteLifts(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Usunięto wyciąg." : ("Usunięto " + size + " wyciągów."));
                break;
            case "activate":
                liftService.activateLifts(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Aktywowano wyciąg." : ("Aktywowano " + size + " wyciągów."));
                break;
            case "deactivate":
                liftService.deactivateLifts(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Dezaktywowano wyciąg." : ("Dezaktywowano " + size + " wyciągów."));
                break;
        }
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/lifts";
    }

}
