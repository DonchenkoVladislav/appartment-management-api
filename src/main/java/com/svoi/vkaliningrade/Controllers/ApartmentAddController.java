package com.svoi.vkaliningrade.Controllers;

import com.svoi.vkaliningrade.Models.ApartmentDescription;
import com.svoi.vkaliningrade.dto.RequestFrontPage;
import com.svoi.vkaliningrade.Repo.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ApartmentAddController {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping("/apartment-add")
    public String getApartmentAddPage() {
        return "apartmentedit";
    }

    @PostMapping("/apartment-add")
    public void saveApartment(@RequestBody RequestFrontPage requestBody){
        ApartmentDescription description = new ApartmentDescription(requestBody);
        apartmentRepository.save(description);
    }

}