package com.svoi.vkaliningrade.Repo;

import com.svoi.vkaliningrade.Models.Tariff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarffRepositiry extends CrudRepository<Tariff, Long> {

};
