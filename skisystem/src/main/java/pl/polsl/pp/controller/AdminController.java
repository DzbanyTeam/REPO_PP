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
import pl.polsl.pp.model.AdminAccount;
import pl.polsl.pp.service.interfaces.IAdminAccountService;
import pl.polsl.pp.service.interfaces.IRoleService;
import pl.polsl.pp.validator.AdminAccountValidator;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    @Qualifier("adminAccountService")
    private IAdminAccountService adminAccountService;

    @Autowired
    @Qualifier("roleService")
    private IRoleService roleService;

    @Autowired
    private AdminAccountValidator adminAccountValidator;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        binder.addValidators(adminAccountValidator);
    }

    public AdminController() {}

    @GetMapping("")
    public String showAdmin() {
        return "cms/home/index";
    }

    @GetMapping("/admins")
    public String showAdmins(Model model){
        model.addAttribute("adminAccounts", adminAccountService.getAllAdminAccounts());
        return "cms/adminAccount/list"; }

    @GetMapping("/admins/edit/{id}")
    public String showAdminsEdit(Model model, @PathVariable Long id){
        model.addAttribute("adminAccount", adminAccountService.getAdminAccountById(id));
        return "cms/adminAccount/edit";
    }

    @GetMapping("/admins/add")
    public String showAdminsAdd(Model model){
        model.addAttribute("adminAccount", new AdminAccount());
        return "cms/adminAccount/edit";
    }

    @PostMapping("/admins/submit")
    public String submitAdmin(@ModelAttribute("adminAccount") @Validated AdminAccount adminAccountRequest, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            model.addAttribute("alertText", "Nie można zapisać administratora. Sprawdź błędy formularza.");
            model.addAttribute("alertType", "danger");
            return "cms/adminAccount/edit";
        }

        adminAccountService.saveAdminAccount(adminAccountRequest);

        redirectAttributes.addFlashAttribute("alertText", "Zapisano administratora.");
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/admins/edit/" + adminAccountRequest.getId();
    }

    @GetMapping("/admins/update")
    public String showAdminsUpdate(@RequestParam(name = "action")String action,@RequestParam(name ="ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes){
        int size = ids.size();

        switch (action) {
            case "delete":
                adminAccountService.deleteAdminAccounts(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Usunięto administatora." : ("Usunięto " + size + " administratorów."));
                break;
            case "activate":
                adminAccountService.activateAdminAccounts(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Aktywowano administatora." : ("Aktywowano " + size + " administratorów."));
                break;
            case "deactivate":
                adminAccountService.deactivateAdminAccounts(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Dezaktywowano administatora." : ("Dezaktywowano " + size + " administratorów."));
                break;
        }
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/admins";
    }
}
