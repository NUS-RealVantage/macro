package com.example.macro.service;

import com.example.macro.model.Country;
import com.example.macro.model.CountryMacro;
import com.example.macro.repository.CountryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.macro.dto.MacroRequestDTO;
import com.example.macro.dto.MacroResponseDTO;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class MacroServiceImpl implements MacroService{

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	MacroDataPopulatorService macroDataPopulatorService;

	@Override
	public MacroResponseDTO getMacro(MacroRequestDTO dto) {
		MacroResponseDTO respDTO = new MacroResponseDTO();
		Optional<Country> opt = getCountry(dto.getCountryName());

		if(!opt.isEmpty()) {
			List<MacroResponseDTO.Macro> macrosDTO = convertTOMacroDTOFrom(opt.get().getMacros());
			respDTO = new MacroResponseDTO();
			respDTO.setData(macrosDTO);
		}
		return respDTO;
	}

	public List<MacroResponseDTO.Macro> convertTOMacroDTOFrom(Set<CountryMacro> macros) {
		List<MacroResponseDTO.Macro> macrosDTO = macros.stream()
				.map(e -> {
					MacroResponseDTO.Macro macroDTO = new MacroResponseDTO.Macro();
					BeanUtils.copyProperties(e, macroDTO);
					return macroDTO;
				})
				.collect(Collectors.toList());

		macrosDTO.sort(Comparator.comparing(MacroResponseDTO.Macro::getYear));
		return macrosDTO;
	}

	public Optional<Country> getCountry(String countryName) {
		Country countryEx = new Country();
		countryEx.setName(countryName);
		Example<Country>  queryCountry = Example.of(countryEx);
		return countryRepository.findOne(queryCountry);
	}
}
