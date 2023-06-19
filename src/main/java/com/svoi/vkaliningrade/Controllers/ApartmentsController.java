package com.svoi.vkaliningrade.Controllers;

import com.svoi.vkaliningrade.dto.ApartmentShortInfo;
import com.svoi.vkaliningrade.dto.RequestFrontPage;
import com.svoi.vkaliningrade.services.ApartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public @ResponseBody List<ApartmentShortInfo> getShortInfoListResponse() {
        return apartmentsService.getApartmentShortInfo();
    }

    @DeleteMapping("/delete")
    public String delete(@RequestParam(defaultValue = "0") Long id){
        apartmentsService.delete(id);
        return "main";
    }

}