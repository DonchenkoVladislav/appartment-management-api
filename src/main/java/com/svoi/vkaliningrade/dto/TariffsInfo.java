package com.svoi.vkaliningrade.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TariffsInfo {

    private String startDate, endDate;
    private int summaryTariff;
}
