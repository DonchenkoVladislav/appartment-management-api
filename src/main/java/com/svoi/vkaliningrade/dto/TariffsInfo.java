package com.svoi.vkaliningrade.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TariffsInfo {

    private String startDate, endDate;
    private int summaryTariff;
}
