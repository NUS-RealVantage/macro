package com.example.macro.service;

import com.example.macro.dto.MacroRequestDTO;
import com.example.macro.dto.MacroResponseDTO;
import com.example.macro.model.Country;
import com.example.macro.repository.CountryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class DeprecatedMacroServiceImpl implements MacroService{

	@Autowired
	private CountryRepository countryRepository;

	@Override
	public MacroResponseDTO getMacro(MacroRequestDTO dto) {

		List<Country> countries = new ArrayList<>();
		countryRepository.findAll().iterator().forEachRemaining(countries::add);

		Country countryEx = new Country();
		countryEx.setName("Australia");
		Example<Country>  queryCountry = Example.of(countryEx);
		Optional<Country> opt = countryRepository.findOne(queryCountry);
		List<MacroResponseDTO.Macro> macrosDTO = opt.orElse(new Country())
				.getMacros()
				.stream()
				.map(e -> {
					MacroResponseDTO.Macro macroDTO = new MacroResponseDTO.Macro();
					BeanUtils.copyProperties(e, macroDTO);
					return macroDTO;
				})
				.collect(Collectors.toList());

		MacroResponseDTO respDTO = new MacroResponseDTO();
		respDTO.setData(macrosDTO);
		return respDTO;
	}
}
