package com.svoi.vkaliningrade.services;

import com.svoi.vkaliningrade.Models.ApartmentDescription;
import com.svoi.vkaliningrade.Models.Tariff;
import com.svoi.vkaliningrade.Repo.ApartmentRepository;
import com.svoi.vkaliningrade.Repo.TarffRepositiry;
import com.svoi.vkaliningrade.dto.ApartmentShortInfo;
import com.svoi.vkaliningrade.dto.RequestFrontPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApartmentsService {

    @Autowired
    private ApartmentRepository apartmentRepository;

    @Autowired
    private TarffRepositiry tarffRepositiry;

    //Сохранить информацию об объекте в БД
    public void save(RequestFrontPage body) {
        ApartmentDescription description = new ApartmentDescription(body);
        apartmentRepository.save(description);

        body.getTariffs().forEach(currentTariff -> {
            Tariff saveTariff = new Tariff(description.getId(), currentTariff);
            tarffRepositiry.save(saveTariff);
        });
    }

    //Сформировать список кратких информаций об объектах для отображения на странице информации
    public List<ApartmentShortInfo> getApartmentShortInfo() {
        List<ApartmentShortInfo> shortInfoList = new ArrayList<>();
        apartmentRepository.findAll().forEach(apartment -> shortInfoList.add(
                        ApartmentShortInfo.builder()
                                .id(apartment.getId())
                                .name(apartment.getName())
                                .city(apartment.getCity())
                                .space(apartment.getSpace())
                                .adult(apartment.getAdult())
                                .children(apartment.getChildren())
                                .fromDay(apartment.getFromDay())
                                .summary(apartment.getSummary())
                                .build()
                )
        );

        return shortInfoList;
    }
}
