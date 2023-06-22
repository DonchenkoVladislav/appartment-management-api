package com.svoi.vkaliningrade.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class ApartmentInfo {

    private Long id;
    private String description, view, name, city, coordinates, beds, conveniences, services;
    private int space, adult, children, from, summary;
    private List<TariffsInfo> tariffs;

    public ApartmentInfo() {
    }
}
