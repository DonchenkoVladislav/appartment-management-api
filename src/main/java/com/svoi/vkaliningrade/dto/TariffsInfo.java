package com.svoi.vkaliningrade.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
public class TariffsInfo {

    private String startDate, endDate;
private int  summaryTariff;

}
