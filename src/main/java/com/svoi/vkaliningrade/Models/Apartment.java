package com.svoi.vkaliningrade.Models;

import dto.TariffsInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description, view, name, city, coordinates;
    private int space, adult, children, from, summary;
    private List<String> beds, conveniences, services;
//    private List<photos> photos;
    private List<TariffsInfo> tariffs;

    public Apartment() {
    }

    public Apartment(String description, String view, String name, String city, String coordinates, int space,
                     int adult, int children, int from, int summary, List<String> beds, List<String> conveniences,
                     List<String> services, List<TariffsInfo> tariffs) {
        this.description = description;
        this.view = view;
        this.name = name;
        this.city = city;
        this.coordinates = coordinates;
        this.space = space;
        this.adult = adult;
        this.children = children;
        this.from = from;
        this.summary = summary;
        this.beds = beds;
        this.conveniences = conveniences;
        this.services = services;
        this.tariffs = tariffs;
    }
}
