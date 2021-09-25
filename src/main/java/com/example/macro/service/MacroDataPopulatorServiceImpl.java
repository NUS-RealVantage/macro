package com.example.macro.service;

import com.example.macro.dto.WorldBankAPIResponseDTO;
import com.example.macro.dto.WorldBankPopulateMacroDTO;
import com.example.macro.model.Country;
import com.example.macro.model.CountryMacro;
import com.example.macro.repository.CountryMacroRepository;
import com.example.macro.repository.CountryRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class MacroDataPopulatorServiceImpl implements  MacroDataPopulatorService<WorldBankPopulateMacroDTO> {

    private final String POPULATION_SYMBOL = "SP.POP.TOTL";
    private final String REAL_INTEREST_RATE_SYMBOL = "FR.INR.RINR";
    private final String DEPOSIT_INTEREST_RATE_SYMBOL = "FR.INR.DPST";
    private final String LENDING_INTEREST_RATE_SYMBOL = "FR.INR.LEND";
    private final String GDP_SYMBOL = "NY.GDP.MKTP.CD";
    private final String CPI_SYMBOL = "FP.CPI.TOTL";
    private final String UNEMPLOYMENT_SYMBOL = "SL.UEM.TOTL.NE.ZS";
    private final String FDI_NET_INFLOW_SYMBOL = "BX.KLT.DINV.CD.WD";
    private final String SALARIED_WORKERS = "SL.EMP.WORK.ZS";
    private final String STARTING_YEAR = "1990";
    private final String END_YEAR = "2030";


    @Autowired
    CountryMacroRepository countryMacroRepository;

    @Autowired
    CountryRepository countryRepository;

    @Override
    public void populate(WorldBankPopulateMacroDTO dto) {
        List<Country> countryList = getAllCountries();
        Map<String, List<WorldBankAPIResponseDTO>> respDTOByCountry =
                getWorldBankRespDTOByCountry(getCountryCodes(countryList));
        countryList.forEach(country ->
                saveDTOListToMacro(country, respDTOByCountry.get(country.getIsoCode().toLowerCase())));
    }

    private Map<String, List<WorldBankAPIResponseDTO>> getWorldBankRespDTOByCountry (String countryCodes) {
        String worldBankUrl = generateWorldBankUrl(countryCodes);
        List<WorldBankAPIResponseDTO> worldBankResponseDTOList = getDataFromWorldBank(worldBankUrl);
        Map<String, List<WorldBankAPIResponseDTO>> respDTOByCountry = worldBankResponseDTOList.stream()
                .collect(Collectors.groupingBy(e -> e.getCountry().getId().toLowerCase()));

        return respDTOByCountry;
    }
    private List<Country> getAllCountries() {
        List<Country> countryList = new ArrayList<>();

        for(Country country: countryRepository.findAll()) {
            if(!country.getMacros().isEmpty()) {
                country.getMacros().removeAll(country.getMacros());
            }
            countryList.add(country);
        }

        return countryList;
    }

    private void saveDTOListToMacro(Country country, List<WorldBankAPIResponseDTO> dto) {
        Map<String, Map<String, BigDecimal>> map = dto.stream()
                .filter(e -> e.getValue() != null)
                .collect(
                        Collectors.groupingBy(WorldBankAPIResponseDTO::getDate,
                                Collectors.toMap(e -> e.getIndicator().getId(), e -> e.getValue()))
                );

        List<CountryMacro> countriesMacro = map.entrySet().stream()
                .map(e -> convertToCountryMacroFrom(country, e.getKey(), e.getValue()))
                .collect(Collectors.toList());
        countryMacroRepository.saveAll(countriesMacro);
    }

    private String getCountryCodes(List<Country> countries) {
        return countries.stream().map(e -> e.getIsoCode()).collect(Collectors.joining(";"));
    }

    public String generateWorldBankUrl(String countryCodes) {

        return String.format("https://api.worldbank.org/v2/country/%s/indicator/%s;%s;%s;%s;%s;%s;%s;%s;%s?source=2&format=json&per_page=10000&date=%s:%s",
                countryCodes,POPULATION_SYMBOL, CPI_SYMBOL, GDP_SYMBOL, REAL_INTEREST_RATE_SYMBOL,
                DEPOSIT_INTEREST_RATE_SYMBOL, LENDING_INTEREST_RATE_SYMBOL, UNEMPLOYMENT_SYMBOL,
                FDI_NET_INFLOW_SYMBOL, SALARIED_WORKERS, STARTING_YEAR, END_YEAR);


    }

    private CountryMacro convertToCountryMacroFrom(Country country, String date, Map<String, BigDecimal> valueFromIndicatorId) {
        CountryMacro macro = new CountryMacro();
        macro.setCountry(country);
        macro.setYear(date);
        macro.setFdi(valueFromIndicatorId.get(FDI_NET_INFLOW_SYMBOL));
        macro.setGdp(valueFromIndicatorId.get(GDP_SYMBOL));
        macro.setCpi(valueFromIndicatorId.get(CPI_SYMBOL));
        macro.setUnemployment(valueFromIndicatorId.get(UNEMPLOYMENT_SYMBOL));
        macro.setLendingInterestRate(valueFromIndicatorId.get(LENDING_INTEREST_RATE_SYMBOL));
        macro.setDepositInterestRate(valueFromIndicatorId.get(DEPOSIT_INTEREST_RATE_SYMBOL));
        macro.setRealInterestRate(valueFromIndicatorId.get(REAL_INTEREST_RATE_SYMBOL));
        macro.setPopulation(valueFromIndicatorId.get(POPULATION_SYMBOL));
        macro.setSalariedWorker(valueFromIndicatorId.get(SALARIED_WORKERS));
        return macro;
    }

    private List<WorldBankAPIResponseDTO> getDataFromWorldBank(String url) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

        List<WorldBankAPIResponseDTO> result = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        try{
            JsonNode root = mapper.readTree(response.getBody());
            ObjectReader reader = mapper.readerFor(new TypeReference<List<WorldBankAPIResponseDTO>>() {});
            result = reader.readValue(root.get(1));

        }catch(Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(new HashSet<>(result));
    }

    public static void main (String [] args) {
        MacroDataPopulatorServiceImpl a = new MacroDataPopulatorServiceImpl();
        a.generateWorldBankUrl("au");
    }
}
