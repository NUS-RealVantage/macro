package com.example.macro.repository;

import com.example.macro.model.Country;
import com.example.macro.model.CountryMacro;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryMacroRepository extends CrudRepository<CountryMacro, Long>, QueryByExampleExecutor<CountryMacro> {
}
