package com.svoi.vkaliningrade.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RequestFrontPage {

    private Long id;
    private String description, view, name, city, coordinates, beds, conveniences, services;
    private int space, adult, children, from, summary;
    private List<TariffsInfo> tariffs;

    public RequestFrontPage() {
    }
}
