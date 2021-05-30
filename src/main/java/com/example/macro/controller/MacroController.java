package com.example.macro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.macro.dto.MacroRequestDTO;
import com.example.macro.dto.MacroResponseDTO;
import com.example.macro.service.MacroService;

@RestController
public class MacroController {

	private final String PREFIX_PATH = "/api/v1";

	@Autowired
	MacroService macroService;
	
	
	@GetMapping(PREFIX_PATH + "/macro")
	public MacroResponseDTO MacroResponseDTO(MacroRequestDTO dto) {
		System.out.println("called");
		return macroService.getMacro(dto);
	}
}
