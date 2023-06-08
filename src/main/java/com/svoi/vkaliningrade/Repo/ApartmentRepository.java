package com.svoi.vkaliningrade.Repo;

import com.svoi.vkaliningrade.Models.ApartmentDescription;
import org.springframework.data.repository.CrudRepository;

public interface ApartmentRepository extends CrudRepository<ApartmentDescription, Long> {
}
