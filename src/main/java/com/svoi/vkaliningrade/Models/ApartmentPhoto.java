package com.svoi.vkaliningrade.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ApartmentPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long apartmentId;

    @Lob
    private byte[] photo;

    public ApartmentPhoto(){}
}
