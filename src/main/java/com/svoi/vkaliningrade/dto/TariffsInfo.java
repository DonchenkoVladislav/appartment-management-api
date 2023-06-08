package com.svoi.vkaliningrade.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@Entity
public class TariffsInfo {

    private Long apartmentId;
    private String startDate, endDate;
private int  summaryTariff;

}
