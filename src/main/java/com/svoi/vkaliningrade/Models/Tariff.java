package com.svoi.vkaliningrade.Models;

import com.svoi.vkaliningrade.dto.TariffsInfo;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Tariff {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long appartmentId;
    private String startDate;
    private String endDate;
    private int summaryTariff;

    public Tariff(){}
    public Tariff(Long appartmentId, TariffsInfo tInfo) {
        this.appartmentId = appartmentId;
        this.startDate = tInfo.getStartDate();
        this.endDate = tInfo.getEndDate();
        this.summaryTariff = tInfo.getSummaryTariff();
    }
}
