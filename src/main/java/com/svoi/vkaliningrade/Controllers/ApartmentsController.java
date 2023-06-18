package com.svoi.vkaliningrade.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApartmentsController {

    @GetMapping("/apartments")
    public String getApartmentsPage(){
        return "apartments";
    }

}
