package com.svoi.vkaliningrade.services;

import com.svoi.vkaliningrade.Models.ApartmentDescription;
import com.svoi.vkaliningrade.Models.Tariff;
import com.svoi.vkaliningrade.Repo.ApartmentRepository;
import com.svoi.vkaliningrade.Repo.TarffRepositiry;
import com.svoi.vkaliningrade.dto.ApartmentShortInfo;
import com.svoi.vkaliningrade.dto.RequestFrontPage;
import com.svoi.vkaliningrade.dto.TariffsInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static com.svoi.vkaliningrade.CommonConstants.ALL_OBJECTS_TEXT;

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
        if (name.equals(ALL_OBJECTS_TEXT) & city.equals("-")) {
            return getAllApartmentShortInfo();
        } else if (!name.equals(ALL_OBJECTS_TEXT) & !city.equals("-")) {
            return getAllApartmentShortInfo().stream()
                    .filter(shortInfo -> shortInfo.getName().equals(name))
                    .filter(shortInfo -> shortInfo.getCity().equals(city))
                    .collect(Collectors.toList());
        } else if (!name.equals(ALL_OBJECTS_TEXT) & city.equals("-")) {
            return getAllApartmentShortInfo().stream()
                    .filter(shortInfo -> shortInfo.getName().equals(name))
                    .collect(Collectors.toList());
        } else {
            return getAllApartmentShortInfo().stream()
                    .filter(shortInfo -> shortInfo.getCity().equals(city))
                    .collect(Collectors.toList());
        }
    }

    //Вернуть список всех названий объектов отсортированых по алфавиту
    public List<String> getAllApartmentsName() {
        var names = getAllApartmentsList().stream()
                //Промежуточна операция: Превращаем в каждое ApartmentDescription в String
                .map(ApartmentDescription::getName)
                //Промежуточна операция: Сортируем по алфавиту
                .sorted(new Comparator<String>() {
                    @Override
                    public int compare(String o1, String o2) {
                        return o1.compareTo(o2);
                    }
                })
                //Завершающая операция: Превращаем Stream в List
                .collect(Collectors.toList());

        names.add(ALL_OBJECTS_TEXT);
        return names;
    }

    //Пример билдера
    private List<ApartmentShortInfo> getAllApartmentShortInfo() {
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

    private List<ApartmentDescription> getAllApartmentsList() {
        List<ApartmentDescription> list = new ArrayList<>();
        apartmentRepository.findAll().forEach(list::add);
        return list;
    }
}
