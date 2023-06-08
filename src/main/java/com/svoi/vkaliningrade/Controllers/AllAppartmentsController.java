package com.svoi.vkaliningrade.Controllers;

import org.springframework.web.bind.annotation.GetMapping;

public class AllAppartmentsController {

    @GetMapping("/")
    public String getAllAppartmentAdminPage() {
        return "allAppartmentsPage";
    }
}
