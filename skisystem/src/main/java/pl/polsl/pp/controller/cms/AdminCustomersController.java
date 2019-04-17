package pl.polsl.pp.controller.cms;

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
import pl.polsl.pp.model.CustomerAccount;
import pl.polsl.pp.service.interfaces.IAdminAccountService;
import pl.polsl.pp.service.interfaces.ICustomerAccountService;
import pl.polsl.pp.service.interfaces.IRoleService;
import pl.polsl.pp.validator.AdminAccountValidator;
import pl.polsl.pp.validator.CustomerAccountValidator;

import java.util.List;

@Controller
@RequestMapping("/admin/customers")
public class AdminCustomersController {

    @Autowired
    @Qualifier("customerAccountService")
    private ICustomerAccountService customerAccountService;

    @Autowired
    private CustomerAccountValidator customerAccountValidator;

    @InitBinder
    protected void initBinderCustomer(WebDataBinder binder) {
        binder.addValidators(customerAccountValidator);
    }

    public AdminCustomersController() {
    }

    @GetMapping("")
    public String showCustomers(Model model) {
        model.addAttribute("customerAccounts", customerAccountService.getAllCustomerAccounts());
        return "cms/customerAccount/list";
    }
    @GetMapping("/edit/{id}")
    public String showCustomersEdit(Model model, @PathVariable Long id) {
        model.addAttribute("customerAccount", customerAccountService.getCustomerAccountById(id));
        return "cms/customerAccount/edit";
    }

    @GetMapping("/add")
    public String showCustomersAdd(Model model) {
        model.addAttribute("customerAccount", new CustomerAccount());
        return "cms/customerAccount/edit";
    }

    @PostMapping("/submit")
    public String submitCustomer(@ModelAttribute("customerAccount") @Validated CustomerAccount customerAccountRequest, BindingResult bindingResult, Model model, final RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("alertText", "Nie można zapisać klienta. Sprawdź błędy formularza.");
            model.addAttribute("alertType", "danger");
            return "cms/customerAccount/edit";
        }

        customerAccountService.saveCustomerAccount(customerAccountRequest);

        redirectAttributes.addFlashAttribute("alertText", "Zapisano klienta.");
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/customers/edit/" + customerAccountRequest.getId();
    }

    @GetMapping("/update")
    public String showCustomersUpdate(@RequestParam(name = "action") String action, @RequestParam(name = "ids[]", required = false) List<Long> ids, final RedirectAttributes redirectAttributes) {
        int size = ids.size();

        switch (action) {
            case "delete":
                customerAccountService.deleteCustomerAccounts(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Usunięto klienta." : ("Usunięto " + size + " klienta."));
                break;
            case "activate":
                customerAccountService.activateCustomerAccounts(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Aktywowano klienta." : ("Aktywowano " + size + " klienta."));
                break;
            case "deactivate":
                customerAccountService.deactivateCustomerAccounts(ids);
                redirectAttributes.addFlashAttribute("alertText", size == 1 ? "Dezaktywowano klienta." : ("Dezaktywowano " + size + " klienta."));
                break;
        }
        redirectAttributes.addFlashAttribute("alertType", "success");

        return "redirect:/admin/customers";
    }

}
