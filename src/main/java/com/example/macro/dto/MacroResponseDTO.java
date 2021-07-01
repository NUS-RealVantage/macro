package com.example.macro.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class MacroResponseDTO {

	List<Macro> macros;

	@Getter
	@Setter
	public static class Macro {
		private long id;
		private BigDecimal realInterestRate;
		private BigDecimal depositInterestRate;
		private BigDecimal lendingInterestRate;
		private BigDecimal gdp;
		private BigDecimal cpi;
		private BigDecimal wages;
		private BigDecimal population;
		private BigDecimal unemployment;
		private BigDecimal fdi;
		private String year;
	}

}
