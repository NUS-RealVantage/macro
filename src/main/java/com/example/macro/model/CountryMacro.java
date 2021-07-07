package com.example.macro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Table(name = "countries_macros")
@NoArgsConstructor
public class CountryMacro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne
    @JoinColumn(name="country_id")
    private Country country;

    @Override
    public String toString() {
        return "CountryMacro{" +
                "id=" + id +
                ", realInterestRate=" + realInterestRate +
                ", depositInterestRate=" + depositInterestRate +
                ", lendingInterestRate=" + lendingInterestRate +
                ", gdp=" + gdp +
                ", cpi=" + cpi +
                ", wages=" + wages +
                ", population=" + population +
                ", unemployment=" + unemployment +
                ", fdi=" + fdi +
                ", year='" + year + '\'' +
                ", country=" + country +
                '}';
    }
}
