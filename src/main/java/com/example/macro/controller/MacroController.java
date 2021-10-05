package com.example.macro.controller;

import com.example.macro.model.Country;
import com.example.macro.repository.CountryRepository;
import com.example.macro.service.MacroDataPopulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.macro.dto.MacroRequestDTO;
import com.example.macro.dto.MacroResponseDTO;
import com.example.macro.service.MacroService;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class MacroController {

	private final String PREFIX_PATH = "/api/v1";

	@Autowired
	MacroService macroService;

	@Autowired
	MacroDataPopulatorService macroDataPopulatorService;

	@Autowired
	private CountryRepository countryRepository;
	
	@GetMapping(PREFIX_PATH + "/macros/{countryName}")
	public MacroResponseDTO macro(@PathVariable String countryName,  MacroRequestDTO dto){
		dto.setCountryName(countryName);
		return macroService.getMacro(dto);
	}

	@GetMapping(PREFIX_PATH + "/populator")
	public String populator(MacroRequestDTO dto){
		macroDataPopulatorService.populate(null);
		return "success";
	}
}
