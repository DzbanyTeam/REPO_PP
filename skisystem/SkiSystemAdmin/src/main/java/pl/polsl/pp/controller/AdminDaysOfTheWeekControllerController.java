package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.DayOfTheWeek;
import pl.polsl.pp.service.interfaces.IDayOfTheWeekService;

import java.util.List;

@Controller
@RequestMapping("/admin/days-of-the-week")
public class AdminDaysOfTheWeekControllerController {

    @Autowired
    @Qualifier("dayOfTheWeekServiceInterface")
    private IDayOfTheWeekService dayOfTheWeekService;

    @GetMapping("")
    public String showDaysOfTheWeek(Model model) {
        model.addAttribute("daysOfTheWeek", dayOfTheWeekService.getAllDaysOfTheWeek());
        return "cms/dayOfTheWeek/list";
    }

    @GetMapping("/add")
    public String showDaysOfTheWeekAdd(Model model) {
        model.addAttribute("dayOfTheWeek", new DayOfTheWeek());
        return "cms/dayOfTheWeek/edit";
    }

    @GetMapping("/edit/{id}")
    public String showDaysOfTheWeekEdit(Model model, @PathVariable Long id) {
        model.addAttribute("dayOfTheWeek", dayOfTheWeekService.getDayOfTheWeekById(id));
        return "cms/dayOfTheWeek/edit";
    }

    @PostMapping("/submit")
    public String submitDayOfTheWeek(@ModelAttribute("dayOfTheWeek") DayOfTheWeek dayOfTheWeekRequest, Model model, final RedirectAttributes redirectAttributes) {
        // DONE: TODO: implement
        dayOfTheWeekService.saveDayOfTheWeek(dayOfTheWeekRequest);

        redirectAttributes.addFlashAttribute("alertText", "Zapisano poziom trudności.");
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/days-of-the-week/edit/" + dayOfTheWeekRequest.getId();
    }

    @GetMapping("/update")
    public String showDaysOfTheWeekUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        int size = ids.size();
        switch (action) {
            case "delete":
                dayOfTheWeekService.deleteDaysOfTheWeek(ids);

                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Usunięto poziom trudności." : ("Usunięto " + size + " poziomów trudności."));
                break;
        }
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/days-of-the-week";
    }
}
