package com.example.macro.service;

import com.example.macro.dto.BasePopulateMacroDTO;

public  interface MacroDataPopulatorService <T extends BasePopulateMacroDTO> {
    public void populate(T  dto);
}
