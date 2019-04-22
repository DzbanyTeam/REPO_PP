package pl.polsl.pp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import pl.polsl.pp.model.AdminAccount;

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
