package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.BusinessHours;
import pl.polsl.pp.model.DayOfTheWeek;
import pl.polsl.pp.model.Slope;
import pl.polsl.pp.model.SlopeBusinessHours;
import pl.polsl.pp.service.interfaces.*;

import java.sql.Time;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Autowired
    @Qualifier("slopeBusinessHoursServiceInterface")
    private ISlopeBusinessHoursService slopeBusinessHoursService;

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
        HashMap<DayOfTheWeek, SlopeBusinessHours> dayOfTheWeekSlopeBusinessHoursHashMap = new HashMap<DayOfTheWeek, SlopeBusinessHours>();

        slopeBusinessHoursService.getAllSlopeBusinessHours().forEach(sb ->{
            dayOfTheWeekSlopeBusinessHoursHashMap.put(sb.getDayOfTheWeek(),sb);
        });
        model.addAttribute("businessHours", dayOfTheWeekSlopeBusinessHoursHashMap);
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
    public String submitSlope(@ModelAttribute("slope") @Validated Slope slopeRequest, @RequestParam(name="businessHours") @DateTimeFormat(pattern = "HH:mm")  List<Date> businessHours, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors() || businessHours.contains(null)) {
            model.addAttribute("alertText", "Nie można zapisać stoku. Sprawdź błędy formularza.");
            model.addAttribute("alertType", "danger");
            model.addAttribute("difficulties", difficultyService.getAllDifficulties());
            model.addAttribute("lifts", liftService.getAllLifts());
            model.addAttribute("daysOfTheWeek", dayOfTheWeekService.getAllDaysOfTheWeek());
            model.addAttribute("businessHours", new HashMap<DayOfTheWeek, SlopeBusinessHours>());
            return "cms/slope/edit";
        }

        slopeService.saveSlope(slopeRequest);

        List<DayOfTheWeek> dayOfTheWeeks = dayOfTheWeekService.getAllDaysOfTheWeek();
        int i = 0;
        for(DayOfTheWeek dayOfTheWeek : dayOfTheWeeks){
            slopeBusinessHoursService.deleteSlopeBusinnesHoursBySlopeIdAndDayId(slopeRequest.getId(),dayOfTheWeek.getId());
            SlopeBusinessHours slopeBusinessHours = new SlopeBusinessHours(dayOfTheWeek,new Time(businessHours.get(i).getTime()),new Time(businessHours.get(i+1).getTime()),slopeRequest);
            i += 2;
            slopeBusinessHoursService.saveSlopeBusinessHours(slopeBusinessHours);
        }

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
