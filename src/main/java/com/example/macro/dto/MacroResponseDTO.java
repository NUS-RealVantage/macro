package com.example.macro.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MacroResponseDTO {


	List<Field> fields = new ArrayList<>();
	List<Macro> data = new ArrayList<>();

	public MacroResponseDTO () {
		fields.add(new Field("realInterestRate", Format.PERCENTAGE, ""));
		fields.add(new Field("depositInterestRate", Format.PERCENTAGE, ""));
		fields.add(new Field("lendingInterestRate", Format.PERCENTAGE, ""));
		fields.add(new Field("gdp", Format.DOLLAR, "USD"));
		fields.add(new Field("cpi", Format.DOLLAR, "USD"));
		fields.add(new Field("wages", Format.DOLLAR, "USD"));
		fields.add(new Field("population", Format.NONE, ""));
		fields.add(new Field("unemployment", Format.NONE, ""));
		fields.add(new Field("fdi", Format.DOLLAR, "USD"));
		fields.add(new Field("year", Format.NONE, ""));
	}

	public static enum Format{
		PERCENTAGE, DOLLAR, NONE
	}

	@Getter
	@Setter
	public static class Field {

		public Field(String name, Format format, String currency) {
			this.name = name;
			this.format = format;
			this.currency = currency;
		}

		private String name;
		private Format format;
		private String currency;
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
		private BigDecimal wages;
		private BigDecimal population;
		private BigDecimal unemployment;
		private BigDecimal fdi;
		private String year;
	}

}
