package com.svoi.vkaliningrade.Controllers;

import com.svoi.vkaliningrade.Models.ApartmentDescription;
import com.svoi.vkaliningrade.Models.Tariff;
import com.svoi.vkaliningrade.Repo.TarffRepositiry;
import com.svoi.vkaliningrade.dto.RequestFrontPage;
import com.svoi.vkaliningrade.Repo.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ApartmentsController {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private TarffRepositiry tarffRepositiry;

    //Сохранить информацию об объекте в БД
    @PostMapping("/save")
    public String save(@RequestBody RequestFrontPage requestBody){
        ApartmentDescription description = new ApartmentDescription(requestBody);
        apartmentRepository.save(description);

//        for (int i = 0; i < requestBody.getTariffs().size(); i++){
//            TariffsInfo currentTariff = requestBody.getTariffs().get(i);
//            Tariff saveTariff = new Tariff(description.getId(), currentTariff);
//            tarffRepositiry.save(saveTariff);
//        }
//
//        for (int i = 0; i < requestBody.getTariffs().size(); i++){
//            Tariff saveTariff = new Tariff(description.getId(), requestBody.getTariffs().get(i));
//            tarffRepositiry.save(saveTariff);
//        }

//        for(TariffsInfo currentTariff : requestBody.getTariffs()){
//            Tariff saveTariff = new Tariff(description.getId(), currentTariff);
//            tarffRepositiry.save(saveTariff);
//        }

        requestBody.getTariffs().forEach(currentTariff -> {
            Tariff saveTariff = new Tariff(description.getId(), currentTariff);
            tarffRepositiry.save(saveTariff);
        });

        return "main";
    }

}