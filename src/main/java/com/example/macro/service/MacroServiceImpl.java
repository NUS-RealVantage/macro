package com.example.macro.service;

import com.example.macro.dto.WorldBankPopulateMacroDTO;
import com.example.macro.model.Country;
import com.example.macro.repository.CountryRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.macro.dto.MacroRequestDTO;
import com.example.macro.dto.MacroResponseDTO;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MacroServiceImpl implements MacroService{

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	MacroDataPopulatorService macroDataPopulatorService;

	@Override
	public MacroResponseDTO getMacro(MacroRequestDTO dto) {
		List<Country> countries = new ArrayList<>();
		MacroResponseDTO respDTO = new MacroResponseDTO();

		Country countryEx = new Country();
		countryEx.setName(dto.getCountryName());
		Example<Country>  queryCountry = Example.of(countryEx);
		Optional<Country> opt = countryRepository.findOne(queryCountry);

		if(!opt.isEmpty()) {
			List<MacroResponseDTO.Macro> macrosDTO = opt.orElse(new Country())
					.getMacros()
					.stream()
					.map(e -> {
						MacroResponseDTO.Macro macroDTO = new MacroResponseDTO.Macro();
						BeanUtils.copyProperties(e, macroDTO);
						return macroDTO;
					})
					.collect(Collectors.toList());

			macrosDTO.sort(Comparator.comparing(MacroResponseDTO.Macro::getYear));
			respDTO = new MacroResponseDTO();
			respDTO.setMacros(macrosDTO);
		}
		return respDTO;
	}
}
