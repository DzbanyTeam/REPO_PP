package pl.polsl.pp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String showAdmin() {
        return "adminPages/admin";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String showLoginPost(){
        return "adminPages/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginGet(){
        return "adminPages/login";
    }

    @RequestMapping(value = "/admins", method = RequestMethod.GET)
    public String showAdmins(){
        return "adminPages/admins";
    }

    @RequestMapping(value = "/admins/edit/{id}", method = RequestMethod.GET)
    public String showAdminsEdit(@PathVariable String id){
        return "adminPages/edit";
    }

    @RequestMapping(value = "/admins/add", method = RequestMethod.GET)
    public String showAdminsAdd(){
        return "adminPages/add";
    }

    @RequestMapping(value = "/admins/submit", method = RequestMethod.POST)
    public String showAdminsSubmit(){
        return "adminPages/submit";
    }

    @RequestMapping(value = "/admins/update", method = RequestMethod.GET)
    public String showAdminsUpdate(){
        return "adminPages/update";
    }
}
