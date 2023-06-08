package com.svoi.vkaliningrade.Models;

import com.svoi.vkaliningrade.dto.RequestFrontPage;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
//@Entity
public class ApartmentDescription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description, view, name, city, coordinates, beds, conveniences, services;
    private int space, adult, children, from, summary;

    public ApartmentDescription() {
    }

    public ApartmentDescription(RequestFrontPage responseApartment) {
        this.description = responseApartment.getDescription();
        this.view = responseApartment.getView();
        this.name = responseApartment.getName();
        this.city = responseApartment.getCity();
        this.coordinates = responseApartment.getCoordinates();
        this.space = responseApartment.getSpace();
        this.adult = responseApartment.getAdult();
        this.children = responseApartment.getChildren();
        this.from = responseApartment.getFrom();
        this.summary = responseApartment.getSummary();
        this.beds = responseApartment.getBeds();
        this.conveniences = responseApartment.getConveniences();
        this.services = responseApartment.getServices();
    }
}
