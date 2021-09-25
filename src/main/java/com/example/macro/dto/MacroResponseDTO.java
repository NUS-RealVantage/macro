package com.example.macro.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MacroResponseDTO {

	private final String REAL_INTEREST_RATE = "realInterestRate";
	private final String DEPOSIT_INTEREST_RATE = "depositInterestRate";
	private final String LENDING_INTEREST_RATE = "lendingInterestRate";
	private final String GDP = "gdp";
	private final String CPI = "cpi";
	private final String WAGES = "wages";
	private final String POPULATION = "population";
	private final String UNEMPLOYMENT = "unemployment";
	private final String FDI = "fdi";
	private final String YEAR = "year";
	private final String SALARIED_WORKER = "salariedWorker";

	List<Field> fields = new ArrayList<>();
	List<Macro> data = new ArrayList<>();

	public MacroResponseDTO () {
		fields.add(new Field(REAL_INTEREST_RATE, Format.PERCENTAGE));
		fields.add(new Field(DEPOSIT_INTEREST_RATE, Format.PERCENTAGE));
		fields.add(new Field(LENDING_INTEREST_RATE, Format.PERCENTAGE));
		fields.add(new Field(GDP, Format.DOLLAR, Currency.USD));
		fields.add(new Field(CPI, Format.DOLLAR, Currency.USD));
		fields.add(new Field(WAGES, Format.DOLLAR, Currency.USD));
		fields.add(new Field(POPULATION));
		fields.add(new Field(UNEMPLOYMENT));
		fields.add(new Field(FDI, Format.DOLLAR, Currency.USD));
		fields.add(new Field(YEAR));
		fields.add(new Field(SALARIED_WORKER, Format.PERCENTAGE));
	}

	public static enum Format{
		PERCENTAGE, DOLLAR, NONE
	}

	public static enum Currency {
		USD, AUD, SGD
	}

	@Getter
	@Setter
	public static class Field {

		public Field(String name) {
			this.name = name;
			this.format = Format.NONE;
		}

		public Field(String name, Format format) {
			this.name = name;
			this.format = format;
		}

		public Field(String name, Format format, Currency currency) {
			this.name = name;
			this.format = format;
			this.currency = currency;
		}

		private String name;
		private Format format;
		private Currency currency;
	}

	@Getter
	@Setter
	public static class Macro {
		private long id;
		private BigDecimal realInterestRate;
		private BigDecimal depositInterestRate;
		private BigDecimal lendingInterestRate;
		private BigDecimal gdp;
		private BigDecimal cpi;
		private BigDecimal salariedWorker;
		private BigDecimal population;
		private BigDecimal unemployment;
		private BigDecimal fdi;
		private String year;
	}

}
