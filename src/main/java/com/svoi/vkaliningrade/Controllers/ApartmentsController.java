package com.svoi.vkaliningrade.Controllers;

import com.svoi.vkaliningrade.dto.ApartmentShortInfo;
import com.svoi.vkaliningrade.dto.RequestFrontPage;
import com.svoi.vkaliningrade.services.ApartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ApartmentsController {

    @Autowired
    private ApartmentsService apartmentsService;

    //Сохранить информацию об объекте в БД
    @PostMapping("/save")
    public String save(@RequestBody RequestFrontPage requestBody){
        apartmentsService.save(requestBody);
        return "main";
    }

    @GetMapping("/info")
    public @ResponseBody List<ApartmentShortInfo> getShortInfoListResponse(
            @RequestParam(defaultValue = "") String name,
            @RequestParam(defaultValue = "") String city) {
        return apartmentsService.getApartmentShortInfo(name, city);
    }

}