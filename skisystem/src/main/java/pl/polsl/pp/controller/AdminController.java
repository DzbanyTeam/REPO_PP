package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.polsl.pp.model.AdminAccount;
import pl.polsl.pp.model.Role;
import pl.polsl.pp.service.AdminAccountServiceInterface;
import pl.polsl.pp.service.RoleServiceInterface;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    @Qualifier("adminAccountService")
    private AdminAccountServiceInterface adminAccountService;

    @Autowired
    @Qualifier("roleService")
    private RoleServiceInterface roleService;

    public AdminController() {}

    @GetMapping("")
    public String showAdmin() {
        return "adminPages/admin";
    }

    @GetMapping("/admins")
    public String showAdmins(Model model){
        model.addAttribute("admins", adminAccountService.getAllAdminAccounts());
        return "adminPages/admins"; }

    @GetMapping("/admins/edit/{id}")
    public String showAdminsEdit(Model model, @PathVariable Long id){
        model.addAttribute("admin", adminAccountService.getAdminAccountById(id));
        return "adminPages/edit"; }

    @GetMapping("/admins/add")
    public String showAdminsAdd(Model model){
        model.addAttribute("admin", new AdminAccount());
        return "adminPages/edit";
    }

    @PostMapping("/admins/submit")
    public String submitAdmin(@ModelAttribute AdminAccount adminAccountRequest, @RequestParam(name = "isActive", required = false) String isActive, @RequestParam(name = "id") Long id) {

        adminAccountRequest.setActive(isActive != null ? true:false);

        if(id.toString().equals("0")){
            adminAccountService.saveAdminAccount(adminAccountRequest);
        } else {
            if(adminAccountRequest.getPassword().isEmpty()) {
                adminAccountRequest.setPassword(adminAccountService.getAdminAccountById(id).getPassword());
                adminAccountService.saveAdminAccountWithoutHashing(adminAccountRequest);
            } else {
                adminAccountService.saveAdminAccount(adminAccountRequest);
            }
        }

        return "redirect:../admins"; }

    @GetMapping("/admins/update")
    public String showAdminsUpdate(@RequestParam(name = "action")String action,@RequestParam(name ="ids[]", required = false) List<Long> ids){

        if(ids != null) {
            switch (action) {
                case "delete":
                    adminAccountService.deleteAdminAccounts(ids);
                    break;
                case "activate":
                    adminAccountService.activateAdminAccounts(ids);
                    break;
                case "deactivate":
                    adminAccountService.deactivateAdminAccounts(ids);
                    break;
            }
        }
        return "redirect:../admins";
    }
}
