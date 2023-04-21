package org.costandino.dataProcessing.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.costandino.dataProcessing.domain.co2Emission.Co2Emission;
import org.costandino.dataProcessing.domain.co2Emission.Co2Emissions;
import org.costandino.dataProcessing.domain.co2Emission.GlobalCo2Emission;
import org.costandino.dataProcessing.services.Co2EmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("co2Emission")
@RequiredArgsConstructor
@Tag(name = "Co2Emission", description = "The co2Emission endpoints")
public class Co2EmissionController extends GlobalRestHandler{

    private final File jsonSchema = new File("src/main/resources/schema/co2/co2Emissions_schema.json");
    private final File xmlSchema = new File("src/main/resources/schema/co2/co2Emissions_schema.xsd");

    private final Co2EmissionService co2EmissionService;

    @Operation(summary = "Creates a co2Emission entity")
    @PostMapping(
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> save(
            @RequestHeader Map<String, String> headers,
            @RequestBody Co2Emission co2Emission
    ) {
        boolean isValid = false;
        switch (headers.get("content-type")) {
            case "application/json" -> isValid = isEntityValidJson(co2Emission, jsonSchema);
            case "application/xml" -> isValid = isEntityValidXml(co2Emission, xmlSchema, "Co2Emission");
        }
        if (isValid) {
            co2EmissionService.save(co2Emission);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Gets all the co2Emission entities in xml format")
    @GetMapping(value = "/xml", produces= {"application/xml"})
    public Co2Emissions findAllXml() {
        return getValidCo2Emissions(co2EmissionService.findAll().getCo2Emission(), xmlSchema, "application/xml");
    }

    @Operation(summary = "Gets all the co2Emission entities in json format")
    @GetMapping(value = "/json", produces= {"application/json"})
    public Co2Emissions findAllJson() {
        return getValidCo2Emissions(co2EmissionService.findAll().getCo2Emission(), jsonSchema, "application/json");
    }

    @Operation(summary = "Gets a specific co2Emission entity in xml format")
    @GetMapping(value = "/xml/{countryCode}/{year}", produces= {"application/xml"})
    public Co2Emission getCo2EmissionXml(
            @PathVariable("countryCode") String countryCode,
            @PathVariable("year") int year
    ) {
        Co2Emission co2Emission = co2EmissionService.findCo2EmissionById(countryCode, year);
        isEntityValidXml(co2Emission, xmlSchema, "Co2Emission");
        return co2Emission;
    }

    @Operation(summary = "Gets a specific co2Emission entity in json format")
    @GetMapping(value = "/xml/{countryCode}/{year}", produces= {"application/json"})
    public Co2Emission getCo2EmissionJson(
            @PathVariable("countryCode") String countryCode,
            @PathVariable("year") int year
    ) {
        Co2Emission co2Emission = co2EmissionService.findCo2EmissionById(countryCode, year);
        isEntityValidJson(co2Emission, xmlSchema);
        return co2Emission;
    }

    @Operation(summary = "Gets all the co2Emission entries by country in xml format")
    @GetMapping(value="/xml/{year}", produces = {"application/xml"})
    public GlobalCo2Emission getCo2EmissionByYearXml(
            @PathVariable("year") int year
    ) {
        var validCo2Emission = getValidCo2Emissions(co2EmissionService.findCo2EmissionByYear(year).getCo2Emission(), xmlSchema, "application/xml").getCo2Emission();
        double totalEmission = 0;
        for (Co2Emission co2Emission: validCo2Emission) {
            totalEmission += co2Emission.getValue();
        }
        return new GlobalCo2Emission(Math.round(totalEmission/validCo2Emission.size()));
    }

    @Operation(summary = "Gets the global co2Emission by year in json format")
    @GetMapping(value="/json/{year}", produces = {"application/json"})
    public GlobalCo2Emission getCo2EmissionByYearJson(
            @PathVariable("year") int year
    ) {
        var validCo2Emission = getValidCo2Emissions(co2EmissionService.findCo2EmissionByYear(year).getCo2Emission(), jsonSchema, "application/json").getCo2Emission();
        double totalEmission = 0;
        for (Co2Emission co2Emission: validCo2Emission) {
            totalEmission += co2Emission.getValue();
        }
        return new GlobalCo2Emission(Math.round(totalEmission/validCo2Emission.size()));
    }

    @Operation(summary = "Updates a specific co2Emission entity")
    @PutMapping(value="/{countryCode}/{year}", consumes = {"application/xml", "application/json"})
    public ResponseEntity<?> update(
            @PathVariable("countryCode") String countryCode,
            @PathVariable("year") int year,
            @RequestBody Co2Emission co2Emission,
            @RequestHeader Map<String, String> headers
    ) {

        boolean isValid = false;
        switch (headers.get("content-type")) {
            case "application/json" -> isValid = isEntityValidJson(co2Emission, jsonSchema);
            case "application/xml" -> isValid = isEntityValidXml(co2Emission, xmlSchema, "Co2Emission");
        }
        if (isValid) {
            co2EmissionService.update(countryCode, year, co2Emission);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Deletes a specific co2Emission entity")
    @DeleteMapping(value="/{countryCode}/{year}")
    public ResponseEntity<?> delete(
            @PathVariable("countryCode") String countryCode,
            @PathVariable("year") int year
    ) {
        co2EmissionService.delete(countryCode, year);
        return ResponseEntity.ok().build();
    }
}
