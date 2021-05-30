package com.example.macro.service;

import com.example.macro.dto.MacroRequestDTO;
import com.example.macro.dto.MacroResponseDTO;

public interface MacroService {

	public MacroResponseDTO getMacro(MacroRequestDTO dto);
}
