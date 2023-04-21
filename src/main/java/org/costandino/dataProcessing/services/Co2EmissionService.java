package org.costandino.dataProcessing.services;

import lombok.RequiredArgsConstructor;
import org.costandino.dataProcessing.dao.jpa.Co2EmissionRepository;
import org.costandino.dataProcessing.domain.co2Emission.Co2Emission;
import org.costandino.dataProcessing.domain.co2Emission.Co2EmissionId;
import org.costandino.dataProcessing.domain.co2Emission.Co2Emissions;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class Co2EmissionService {

    private final Co2EmissionRepository co2EmissionRepository;

    public void save(Co2Emission co2Emission) {
        co2EmissionRepository.save(co2Emission);
    }

    public Co2Emission findCo2EmissionById(String countryCode, int year) {
        Co2EmissionId co2EmissionId = new Co2EmissionId(countryCode, year);
        return co2EmissionRepository.findById(co2EmissionId).orElseThrow(NullPointerException::new);
    }

    public Co2Emissions findCo2EmissionByYear(int year) {
        var co2Emissions = (List<Co2Emission>) co2EmissionRepository.findByYear(year);
        var co2EmissionsSearchResults = new Co2Emissions();
        co2EmissionsSearchResults.setCo2Emission(co2Emissions);
        return co2EmissionsSearchResults;
    }

    public Co2Emissions findAll() {
        var co2Emissions = (List<Co2Emission>) co2EmissionRepository.findAll();
        var co2EmissionsSearchResults = new Co2Emissions();
        co2EmissionsSearchResults.setCo2Emission(co2Emissions);
        return co2EmissionsSearchResults;
    }

    public void delete(String countryCode, int year) {
        findCo2EmissionById(countryCode, year);
        Co2EmissionId co2EmissionId = new Co2EmissionId(countryCode, year);
        co2EmissionRepository.deleteById(co2EmissionId);
    }

    public void update(String countryCode, int year, Co2Emission co2Emission) {
        findCo2EmissionById(countryCode, year);
        co2EmissionRepository.save(co2Emission);
    }
}
