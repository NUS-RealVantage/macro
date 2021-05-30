package com.example.macro.service;

import org.springframework.stereotype.Service;

import com.example.macro.dto.MacroRequestDTO;
import com.example.macro.dto.MacroResponseDTO;

@Service
public class MacroServiceImpl implements MacroService{

	@Override
	public MacroResponseDTO getMacro(MacroRequestDTO dto) {
		MacroResponseDTO respDTO = new MacroResponseDTO();
		respDTO.setGdp("57 billions");
		respDTO.setEmployment("13 millions");
		respDTO.setInterestRate("5%");
		respDTO.setPopulation("25,36 millions");	
		return respDTO;
	}

}
