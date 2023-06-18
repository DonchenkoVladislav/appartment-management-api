package com.svoi.vkaliningrade.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

import java.util.List;

@Getter
public class RequestFrontPage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description, view, name, city, coordinates, beds, conveniences, services;
    private int space, adult, children, from, summary;
    private List<TariffsInfo> tariffs;

    public RequestFrontPage() {
    }
}
