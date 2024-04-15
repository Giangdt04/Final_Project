package com.t3h.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/views/home")
public class AdminController {
    @GetMapping("/admin")
    public String admin(){
        return "/admin/admin";
    }
}
