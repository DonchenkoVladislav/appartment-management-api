package com.svoi.vkaliningrade.Repo;

import com.svoi.vkaliningrade.Models.ApartmentPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApartmentPhotoRepository extends JpaRepository<ApartmentPhoto, Long> {
    List<ApartmentPhoto> findByApartmentId(Long apartmentId);
}
