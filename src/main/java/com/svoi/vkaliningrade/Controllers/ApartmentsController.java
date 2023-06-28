package com.svoi.vkaliningrade.Controllers;

import com.svoi.vkaliningrade.dto.ApartmentShortInfo;
import com.svoi.vkaliningrade.dto.ApartmentInfo;
import com.svoi.vkaliningrade.services.ApartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static com.svoi.vkaliningrade.CommonConstants.ALL_OBJECTS_TEXT;

@Controller
public class ApartmentsController {

    @Autowired
    private ApartmentsService apartmentsService;

    //Сохранить информацию об объекте в БД
    @PostMapping("/save")
    public String save(@RequestBody ApartmentInfo requestBody){
        apartmentsService.save(requestBody);
        return "main";
    }

    @GetMapping("/info")
    public @ResponseBody List<ApartmentShortInfo> getShortInfoListResponse(
            @RequestParam(defaultValue = ALL_OBJECTS_TEXT) String name,
            @RequestParam(defaultValue = "-") String city) {
        return apartmentsService.getApartmentShortInfo(name, city);
    }

    @GetMapping("/nameList")
    public @ResponseBody List<String> getAllApartmentsName () {
        return apartmentsService.getAllApartmentsName();
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam(defaultValue = "0") Long id){
        apartmentsService.delete(id);
        return "main";
    }

    @GetMapping("/edit")
    public @ResponseBody ApartmentInfo editApartmentInfo(@RequestParam(defaultValue = "0") Long id){
       return apartmentsService.getApartment(id);
    }

    @PutMapping("/edit")
    public String edit(@RequestParam(defaultValue = "0") Long id, @RequestBody ApartmentInfo requestBody){
        apartmentsService.edit(id, requestBody);
        return "main";
    }

}