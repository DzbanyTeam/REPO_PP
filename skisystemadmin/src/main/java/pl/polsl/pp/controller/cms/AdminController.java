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
@RequestMapping("/admin")
public class AdminController {


    public AdminController() {
    }

    @GetMapping("")
    public String showAdmin() {
        return "cms/home/index";
    }

}
