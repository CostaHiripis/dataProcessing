package org.costandino.dataProcessing.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.costandino.dataProcessing.domain.agriculture.Agriculture;
import org.costandino.dataProcessing.domain.agriculture.Agricultures;
import org.costandino.dataProcessing.domain.agriculture.GlobalCowsProducedAndSlaughtered;
import org.costandino.dataProcessing.services.AgricultureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Map;


@RestController
@RequestMapping("agriculture")
@RequiredArgsConstructor
@Tag(name = "Agriculture", description = "The agriculture endpoints")
public class AgricultureController extends GlobalRestHandler {

    private final File jsonSchema = new File("src/main/resources/schema/agriculture/agriculture_schema.json");
    private final File xmlSchema = new File("src/main/resources/schema/agriculture/agriculture_schema.xsd");

    private final AgricultureService agricultureService;

    @Operation(summary = "Creates an agriculture entity")
    @PostMapping(
    consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> save(
            @RequestHeader Map<String, String> headers,
            @RequestBody Agriculture agriculture
    ) {
        boolean isValid = false;
        switch (headers.get("content-type")) {
            case "application/json" -> isValid = isEntityValidJson(agriculture, jsonSchema);
            case "application/xml" -> isValid = isEntityValidXml(agriculture, xmlSchema, "Agriculture");
        }
        if (isValid) {
            agricultureService.save(agriculture);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Gets all the agriculture entities in xml format")
    @GetMapping(value = "/xml", produces= {"application/xml"})
    public Agricultures findAllXml() {
        return getValidAgriculture(agricultureService.findAll().getAgriculture(), xmlSchema, "application/xml");
    }

    @Operation(summary = "Gets all the agriculture entities in json format")
    @GetMapping(value = "/json", produces= {"application/json"})
    public Agricultures findAllJson() {
        return getValidAgriculture(agricultureService.findAll().getAgriculture(), jsonSchema, "application/json");
    }

    @Operation(summary = "Gets a specific agriculture entity in xml format")
    @GetMapping(value = "/xml/{areaCode}/{itemCode}/{elementCode}/{year}", produces= {"application/xml"})
    public Agriculture getAgricultureXml(
            @PathVariable("areaCode") int areaCode,
            @PathVariable("itemCode") int itemCode,
            @PathVariable("elementCode") int elementCode,
            @PathVariable("year") int year
    ) {
        Agriculture agriculture = agricultureService.findAgricultureById(areaCode, itemCode, elementCode, year);
        isEntityValidXml(agriculture, xmlSchema, "Agriculture");
        return agriculture;
    }

    @Operation(summary = "Gets a specific agriculture entity in json format")
    @GetMapping(value = "/json/{areaCode}/{itemCode}/{elementCode}/{year}", produces= {"application/json"})
    public Agriculture getAgricultureJson(
            @PathVariable("areaCode") int areaCode,
            @PathVariable("itemCode") int itemCode,
            @PathVariable("elementCode") int elementCode,
            @PathVariable("year") int year
    ) {
        Agriculture agriculture = agricultureService.findAgricultureById(areaCode, itemCode, elementCode, year);
        isEntityValidJson(agriculture, jsonSchema);
        return agriculture;
    }

    @Operation(summary = "Gets the head count of cows produced and slaughtered by year in xml format")
    @GetMapping(value="/xml/{year}", produces = {"application/xml"})
    public GlobalCowsProducedAndSlaughtered getCowsProducedAndSlaughteredByYearXml(
            @PathVariable("year") int year
    ) {
        var validAgriculture = getValidAgriculture(agricultureService.findCowsProducedAndSlaughteredByYear(year).getAgriculture(), xmlSchema, "application/xml").getAgriculture();
        double totalCowsProducedAndSlaughtered = 0;
        for (Agriculture agriculture: validAgriculture) {
            totalCowsProducedAndSlaughtered += agriculture.getValue();
        }
        return new GlobalCowsProducedAndSlaughtered(Math.round(totalCowsProducedAndSlaughtered/validAgriculture.size()));
    }

    @Operation(summary = "Gets the head count of cows produced and slaughtered by year in json format")
    @GetMapping(value="/json/{year}", produces = {"application/json"})
    public GlobalCowsProducedAndSlaughtered getCowsProducedAndSlaughteredByYearJson(
            @PathVariable("year") int year
    ) {
        var validAgriculture = getValidAgriculture(agricultureService.findCowsProducedAndSlaughteredByYear(year).getAgriculture(), jsonSchema, "application/json").getAgriculture();
        double totalCowsProducedAndSlaughtered = 0;
        for (Agriculture agriculture: validAgriculture) {
            totalCowsProducedAndSlaughtered += agriculture.getValue();
        }
        return new GlobalCowsProducedAndSlaughtered(Math.round(totalCowsProducedAndSlaughtered/validAgriculture.size()));
    }

    @Operation(summary = "Updates a specific agriculture entity")
    @PutMapping(value="/{areaCode}/{itemCode}/{elementCode}/{year}", consumes = {"application/xml", "application/json"})
    public ResponseEntity<?> update(
            @PathVariable("areaCode") int areaCode,
            @PathVariable("itemCode") int itemCode,
            @PathVariable("elementCode") int elementCode,
            @PathVariable("year") int year,
            @RequestBody Agriculture agriculture,
            @RequestHeader Map<String, String> headers
    ) {

        boolean isValid = false;
        switch (headers.get("content-type")) {
            case "application/json" -> isValid = isEntityValidJson(agriculture, jsonSchema);
            case "application/xml" -> isValid = isEntityValidXml(agriculture, xmlSchema, "Agriculture");
        }
        if (isValid) {
            agricultureService.update(areaCode, itemCode, elementCode, year, agriculture);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Deletes a specific agriculture entity")
    @DeleteMapping(value = "/{areaCode}/{itemCode}/{elementCode}/{year}")
    public ResponseEntity<?> delete(
            @PathVariable("areaCode") int areaCode,
            @PathVariable("itemCode") int itemCode,
            @PathVariable("elementCode") int elementCode,
            @PathVariable("year") int year
    ) {
        agricultureService.delete(areaCode, itemCode, elementCode, year);
        return ResponseEntity.ok().build();
    }
}
