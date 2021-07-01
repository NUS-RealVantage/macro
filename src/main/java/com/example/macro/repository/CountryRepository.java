package com.example.macro.repository;

import com.example.macro.model.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends CrudRepository<Country, Long>, QueryByExampleExecutor<Country> {
}
