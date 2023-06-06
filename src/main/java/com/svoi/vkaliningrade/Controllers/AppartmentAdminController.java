package com.svoi.vkaliningrade.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppartmentAdminController {

    @GetMapping("/appartment")
    public String getAppartmentAdminEditPage() {
        return "appartmentedit";
    }
}
