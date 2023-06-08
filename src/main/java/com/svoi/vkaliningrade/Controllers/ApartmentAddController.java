package com.svoi.vkaliningrade.Controllers;

import dto.TariffsInfo;
import com.svoi.vkaliningrade.Models.Apartment;
import com.svoi.vkaliningrade.Repo.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ApartmentAddController {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @GetMapping("/apartment-add")
    public String getApartmentAddPage() {
        return "apartmentedit";
    }

    @PostMapping("/apartment-add")
    public String createApartment(@RequestParam String description, @RequestParam String view, @RequestParam String name,
                                  @RequestParam String city, @RequestParam String coordinates, @RequestParam int space,
                                  @RequestParam int adult, @RequestParam int children, @RequestParam int from,
                                  @RequestParam int summary, @RequestParam List<String> beds,
                                  @RequestParam List<String> conveniences, @RequestParam List<String> services,
                                  @RequestParam List<TariffsInfo> tariffs){
        Apartment apartment = new Apartment(description, view, name, city, coordinates, space, adult, children, from,
                summary, beds, conveniences, services, tariffs);
        apartmentRepository.save(apartment);
        return "/apartmentedit";
    }
}