package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.polsl.pp.model.Season;
import pl.polsl.pp.service.interfaces.ISeasonService;

import java.util.List;

@Controller
@RequestMapping("/admin/seasons")
public class AdminSeasonsController {

    @Autowired
    @Qualifier("seasonServiceInterface")
    private ISeasonService seasonService;

    @GetMapping("")
    public String showSeasons(Model model) {
        model.addAttribute("seasons", seasonService.getAllSeasons()); 
        return "cms/season/list";
    }

    @GetMapping("/add")
    public String showSeasonsAdd(Model model) {
        model.addAttribute("season", new Season());
        return "cms/season/edit";
    }

    @GetMapping("/edit/{id}")
    public String showSeasonsEdit(Model model, @PathVariable Long id) {
        model.addAttribute("season", seasonService.getSeasonById(id)); 
        return "cms/season/edit";
    }

    @PostMapping("/submit")
    public String submitSeason(@ModelAttribute("season") Season seasonRequest, Model model, final RedirectAttributes redirectAttributes) {
        seasonService.saveSeason(seasonRequest);

        redirectAttributes.addFlashAttribute("alertText", "Zapisano sezon.");
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/seasons/edit/" + seasonRequest.getId();
    }

    @GetMapping("/update")
    public String showSeasonsUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        int size = ids.size();
        switch (action) {
            case "delete":
                seasonService.deleteSeasons(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Usunięto sezon." : ("Usunięto " + size + " sezonów."));
                break;
            case "activate":
                seasonService.activateSeasons(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Aktywowano sezon." : ("Aktywowano " + size + " sezonów."));
                break;
            case "deactivate":
                seasonService.deactivateSeasons(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Dezaktywowano sezon." : ("Dezaktywowano " + size + " sezonów."));
                break;
        }
        redirectAttributes.addFlashAttribute("alertType", "success");
        return "redirect:/admin/seasons";
    }
}
