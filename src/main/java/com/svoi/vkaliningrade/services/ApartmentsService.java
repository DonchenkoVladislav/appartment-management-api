package com.svoi.vkaliningrade.services;

import com.svoi.vkaliningrade.Models.ApartmentDescription;
import com.svoi.vkaliningrade.Models.Tariff;
import com.svoi.vkaliningrade.Repo.ApartmentRepository;
import com.svoi.vkaliningrade.Repo.TarffRepositiry;
import com.svoi.vkaliningrade.dto.ApartmentShortInfo;
import com.svoi.vkaliningrade.dto.ApartmentInfo;
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

    private List<Tariff> getAllTariffList() {
        List<Tariff> list = new ArrayList<>();
        tarffRepositiry.findAll().forEach(list::add);
        return list;
    }

    //Сохранить информацию об объекте в БД
    public void save(ApartmentInfo body) {
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


    public void delete(Long id) {
        apartmentRepository.deleteById(id);
        getAllTariffList().forEach(
                tariff -> {
                    if (id.equals((tariff.getAppartmentId()))) {
                        tarffRepositiry.delete(tariff);
                    }
                }
        );
    }

    public ApartmentInfo getApartment(Long id) {
        ApartmentDescription description = apartmentRepository.findById(id).get();

        List<TariffsInfo> findTariff = new ArrayList<>();

        for (Tariff tariff : getAllTariffList()) {
            if (id.equals(tariff.getAppartmentId())) {
                findTariff.add(
                        TariffsInfo.builder()
                                .startDate(tariff.getStartDate())
                                .endDate(tariff.getEndDate())
                                .summaryTariff(tariff.getSummaryTariff())
                                .build()
                );
            }
        }

        return ApartmentInfo.builder()
                .id(description.getId())
                .description(description.getDescription())
                .view(description.getView())
                .name(description.getName())
                .city(description.getCity())
                .coordinates(description.getCoordinates())
                .beds(description.getBeds())
                .conveniences(description.getConveniences())
                .services(description.getServices())
                .space(description.getSpace())
                .adult(description.getAdult())
                .children(description.getChildren())
                .from(description.getFromDay())
                .summary(description.getSummary())
                .tariffs(findTariff)
                .build();

    }

    public void edit(Long id, ApartmentInfo requestBody) {
        // Поиск объекта в БД по id
        ApartmentDescription description = apartmentRepository.findById(id)
                .orElseThrow(
                        () -> new NoSuchElementException(String.format(
                                "Объект с id '%s' не найден в базе", id)
                        )
                );

        // Замена данных объекта данными из формы
        description.setDescription(requestBody.getDescription());
        description.setView(requestBody.getView());
        description.setName(requestBody.getName());
        description.setCity(requestBody.getCity());
        description.setCoordinates(requestBody.getCoordinates());
        description.setSpace(requestBody.getSpace());
        description.setAdult(requestBody.getAdult());
        description.setChildren(requestBody.getChildren());
        description.setFromDay(requestBody.getFrom());
        description.setSummary(requestBody.getSummary());
        description.setBeds(requestBody.getBeds());
        description.setConveniences(requestBody.getConveniences());
        description.setServices(requestBody.getServices());

        // Сохранение данных в базу после редактирования(без тарифов)
        apartmentRepository.save(description);


        //Создаем список(List) всех тарифов -> превращаем в поток(Stream) -> фильтруем(filter) тарифы у которых
        //ApartmentId совпадает id из параметров -> превращаем стрим(Stream) обратно в список(List)

        List<Tariff> readyToDeleteTariffs = getAllTariffList().stream()
                .filter(tariff -> tariff.getAppartmentId().equals(id))
                .toList();

        // Удаляем из БД каждый элемент из ранее созданного списка
        readyToDeleteTariffs.forEach(tariff -> tarffRepositiry.delete(tariff));

        // Получаем список тарифов из формы на странице ->  -> сохраняем в БД
        // С помощью цикла forEach на каждой итерации создаем объект типа "Tariff"
        requestBody.getTariffs().forEach(currentTariff -> {
            Tariff saveTariff = new Tariff(description.getId(), currentTariff);
            tarffRepositiry.save(saveTariff);
        });


//        for (int i = 0; i < requestBody.getTariffs().size(); i++) {
//        }
//
//        getAllTariffList().forEach(
//                tariff -> {
//                    if (id.equals((tariff.getAppartmentId()))) {
//                        tariff.setSummaryTariff();
//                        tariff.setEndDate();
//                        tariff.setEndDate();
//                        tarffRepositiry.save(tariff);
//                    }
//                }
//        );
    }
}
