package com.example.macro.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Getter
@Setter
public class WorldBankAPIResponseDTO {

    private Indicator indicator;
    private Country country;
    private String countryiso3code;
    private String date;
    private BigDecimal value;
    private String scale;
    private String unit;

    @JsonProperty("obs_status")
    private String obsStatus;
    private BigDecimal decimal;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WorldBankAPIResponseDTO that = (WorldBankAPIResponseDTO) o;
        return indicator.equals(that.indicator) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(indicator, date);
    }

    @Getter
    @Setter
    public static class Indicator {
        private String id;
        private String value;

        @Override
        public String toString() {
            return "Indicator{" +
                    "id='" + id + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Indicator indicator = (Indicator) o;
            return Objects.equals(id, indicator.id) && Objects.equals(value, indicator.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id, value);
        }
    }

    @Getter
    @Setter
    public static class Country {
        private String id;
        private String value;

        @Override
        public String toString() {
            return "Country{" +
                    "id='" + id + '\'' +
                    ", value='" + value + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "WorldBankAPIResponseDTO{" +
                "indicator=" + indicator +
                ", country=" + country +
                ", countryiso3code='" + countryiso3code + '\'' +
                ", date='" + date + '\'' +
                ", value=" + value +
                ", scale='" + scale + '\'' +
                ", unit='" + unit + '\'' +
                ", obsStatus='" + obsStatus + '\'' +
                ", decimal=" + decimal +
                '}';
    }
}
