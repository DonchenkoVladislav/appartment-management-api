package com.svoi.vkaliningrade.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ApartmentShortInfo {

    private Long id;
    private String name;
    private String city;
    private int space;
    private int adult;
    private int children;
    private int fromDay;
    private int summary;
}
