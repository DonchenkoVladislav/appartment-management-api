package com.svoi.vkaliningrade.Repo;

import com.svoi.vkaliningrade.Models.ApartmentDescription;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApartmentRepository extends CrudRepository<ApartmentDescription, Long> {
}
