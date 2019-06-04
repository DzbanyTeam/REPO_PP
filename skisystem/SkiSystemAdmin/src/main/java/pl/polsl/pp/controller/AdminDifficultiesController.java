package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.Difficulty;
import pl.polsl.pp.service.interfaces.IDifficultyService;

import java.util.List;

@Controller
@RequestMapping("/admin/difficulties")
public class AdminDifficultiesController {

    @Autowired
    @Qualifier("difficultyServiceInterface")
    private IDifficultyService difficultyService;

    @GetMapping("")
    public String showDifficulties(Model model) {
        model.addAttribute("difficulties", difficultyService.getAllDifficulties());
        return "cms/difficulty/list";
    }

    @GetMapping("/add")
    public String showDifficultiesAdd(Model model) {
        model.addAttribute("difficulty", new Difficulty());
        return "cms/difficulty/edit";
    }

    @GetMapping("/edit/{id}")
    public String showDifficultiesEdit(Model model, @PathVariable Long id) {
        model.addAttribute("difficulty", difficultyService.getDifficultyById(id));
        return "cms/difficulty/edit";
    }

    @PostMapping("/submit")
    public String submitDifficulty(@ModelAttribute("difficulty") Difficulty difficultyRequest, Model model, final RedirectAttributes redirectAttributes) {
        // DONE: TODO: implement
        difficultyService.saveDifficulty(difficultyRequest);

        redirectAttributes.addFlashAttribute("alertText", "Zapisano poziom trudności.");
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/difficulties/edit/" + difficultyRequest.getId();
    }

    @GetMapping("/update")
    public String showDifficultiesUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        int size = ids.size();
        switch (action) {
            case "delete":
                difficultyService.deleteDifficulties(ids);

                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Usunięto poziom trudności." : ("Usunięto " + size + " poziomów trudności."));
                break;
        }
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/difficulties";
    }
}
