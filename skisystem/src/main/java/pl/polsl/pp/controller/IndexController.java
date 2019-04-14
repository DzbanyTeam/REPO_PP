package pl.polsl.pp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.polsl.pp.model.CustomerAccount;
import pl.polsl.pp.service.interfaces.ICustomerAccountService;

@Controller
@RequestMapping("")
public class IndexController {

    @Autowired
    @Qualifier("customerAccountService")
    private ICustomerAccountService customerAccountService;

    @GetMapping("/")
    public String showIndexPage(){
        return "index";
    }

    @GetMapping("/prices")
    public String showPrices() {
        return "index";
    }

    @GetMapping("/lifts")
    public String showLifts() {
        return "index";
    }

    @GetMapping("/slopes/{id}")
    public String showSlopes(@PathVariable String id) {
        CustomerAccount ca = new CustomerAccount("Username"+ id,"Password",true,"Pawel","Debil","dada@o2.pl","dada");
        customerAccountService.saveCustomerAccount(ca);
        return "index";
    }

}
