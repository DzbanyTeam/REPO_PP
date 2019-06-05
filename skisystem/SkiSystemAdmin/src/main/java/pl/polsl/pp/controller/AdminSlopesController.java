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
import pl.polsl.pp.model.DayOfTheWeek;
import pl.polsl.pp.model.Slope;
import pl.polsl.pp.model.SlopeBusinessHours;
import pl.polsl.pp.service.interfaces.IDayOfTheWeekService;
import pl.polsl.pp.service.interfaces.IDifficultyService;
import pl.polsl.pp.service.interfaces.ILiftService;
import pl.polsl.pp.service.interfaces.ISlopeService;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/admin/slopes")
public class AdminSlopesController {

    @Autowired
    @Qualifier("slopeServiceInterface")
    private ISlopeService slopeService;

    @Autowired
    @Qualifier("difficultyServiceInterface")
    private IDifficultyService difficultyService;

    @Autowired
    @Qualifier("liftServiceInterface")
    private ILiftService liftService;

    @Autowired
    @Qualifier("dayOfTheWeekServiceInterface")
    private IDayOfTheWeekService dayOfTheWeekService;

    //@Autowired
    //private SlopeValidator slopeValidator;

    @InitBinder
    protected void initBinderSlope(WebDataBinder binder) {
        //binder.addValidators(slopeValidator);
    }

    public AdminSlopesController() {
    }

    @GetMapping("")
    public String showSlopes(Model model) {
        model.addAttribute("slopes", slopeService.getAllSlopes());
        return "cms/slope/list";
    }
    @GetMapping("/edit/{id}")
    public String showSlopesEdit(Model model, @PathVariable Long id) {
        model.addAttribute("slope", slopeService.getSlopeById(id));
        model.addAttribute("difficulties", difficultyService.getAllDifficulties());
        model.addAttribute("lifts", liftService.getAllLifts());
        model.addAttribute("daysOfTheWeek", dayOfTheWeekService.getAllDaysOfTheWeek());
        model.addAttribute("businessHours", new HashMap<DayOfTheWeek, SlopeBusinessHours>()); // TODO Get this slope's business hours in a map identified by day of the week
        return "cms/slope/edit";
    }

    @GetMapping("/add")
    public String showSlopesAdd(Model model) {
        model.addAttribute("slope", new Slope());
        model.addAttribute("difficulties", difficultyService.getAllDifficulties());
        model.addAttribute("lifts", liftService.getAllLifts());
        model.addAttribute("daysOfTheWeek", dayOfTheWeekService.getAllDaysOfTheWeek());
        model.addAttribute("businessHours", new HashMap<DayOfTheWeek, SlopeBusinessHours>());
        return "cms/slope/edit";
    }

    @PostMapping("/submit")
    public String submitSlope(@ModelAttribute("slope") @Validated Slope slopeRequest, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("alertText", "Nie można zapisać stoku. Sprawdź błędy formularza.");
            model.addAttribute("alertType", "danger");
            return "cms/slope/edit";
        }

        slopeService.saveSlope(slopeRequest);

        redirectAttributes.addFlashAttribute("alertText", "Zapisano stok.");
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/slopes/edit/" + slopeRequest.getId();
    }

    @GetMapping("/update")
    public String showSlopesUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        int size = ids.size();

        switch (action) {
            case "delete":
                slopeService.deleteSlopes(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Usunięto stok." : ("Usunięto " + size + " stoków."));
                break;
            case "activate":
                slopeService.activateSlopes(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Aktywowano stok." : ("Aktywowano " + size + " stoków."));
                break;
            case "deactivate":
                slopeService.deactivateSlopes(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Dezaktywowano stok." : ("Dezaktywowano " + size + " stoków."));
                break;
        }
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/slopes";
    }

}
