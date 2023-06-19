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
import java.util.stream.Collectors;

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
    public List<ApartmentShortInfo> getApartmentShortInfo(String name, String city) {
        if (name.equals("") & city.equals("-")) {
            return getAllApartmentShortInfo(apartmentRepository.findAll());
        } else if (!name.equals("") & !city.equals("-")) {
            return getAllApartmentShortInfo(apartmentRepository.findAll()).stream()
                    .filter(shortInfo -> shortInfo.getName().equals(name))
                    .filter(shortInfo -> shortInfo.getCity().equals(city))
                    .collect(Collectors.toList());
        } else if (!name.equals("") & city.equals("-")) {
            return getAllApartmentShortInfo(apartmentRepository.findAll()).stream()
                    .filter(shortInfo -> shortInfo.getName().equals(name))
                    .collect(Collectors.toList());
        } else {
            return getAllApartmentShortInfo(apartmentRepository.findAll()).stream()
                    .filter(shortInfo -> shortInfo.getCity().equals(city))
                    .collect(Collectors.toList());
        }
    }

    private List<ApartmentShortInfo> getAllApartmentShortInfo(Iterable<ApartmentDescription> iterable) {
        List<ApartmentShortInfo> shortInfoList = new ArrayList<>();
        iterable.forEach(apartment -> shortInfoList.add(
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
