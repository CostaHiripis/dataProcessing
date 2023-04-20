package org.costandino.dataProcessing.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.costandino.dataProcessing.domain.Agriculture.Agriculture;
import org.costandino.dataProcessing.domain.Agriculture.Agricultures;
import org.costandino.dataProcessing.services.AgricultureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Map;


@RestController
@RequestMapping("api/v1/agriculture")
@RequiredArgsConstructor
public class AgricultureController extends GlobalRestHandler {

    private final File jsonSchema = new File("src/main/resources/schema/agriculture/agriculture_schema.json");
    private final File xmlSchema = new File("src/main/resources/schema/agriculture/agriculture_schema.xsd");

    private final AgricultureService agricultureService;

    @Operation(summary = "Creates an agriculture entity")
    @PostMapping(
    consumes = {"application/json", "application/xml"})
    public ResponseEntity<Object> save(
            @RequestHeader Map<String, String> headers,
            @RequestBody Agriculture agriculture
    ) {
        boolean isValid = false;
        switch (headers.get("content-type")) {
            case "application/json" -> isValid = isEntityValidJson(agriculture, jsonSchema);
            case "application/xml" -> isValid = isEntityValidXml(agriculture, xmlSchema);
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
        Agriculture agriculture = agricultureService.findAgricultureByAreaCodeItemCodeElementCodeAndYear(areaCode, itemCode, elementCode, year);
        isEntityValidXml(agriculture, xmlSchema);
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
        Agriculture agriculture = agricultureService.findAgricultureByAreaCodeItemCodeElementCodeAndYear(areaCode, itemCode, elementCode, year);
        isEntityValidJson(agriculture, jsonSchema);
        return agriculture;
    }

    @Operation(summary = "Gets all the agriculture entries of cows produced and slaughtered by country in xml format")
    @GetMapping(value="/xml/{area}", produces = {"application/xml"})
    public Agricultures getCowsProducedAndSlaughteredByCountryXml(
            @PathVariable("area") String area
    ) {
        return getValidAgriculture(agricultureService.findCowsProducedAndSlaughteredByArea(area).getAgriculture(), xmlSchema, "application/xml");
    }

    @Operation(summary = "Gets all the agriculture entries of cows produced and slaughtered by country in json format")
    @GetMapping(value="/json/{area}", produces = {"application/json"})
    public Agricultures getCowsProducedAndSlaughteredByCountryJson(
            @PathVariable("area") String area
    ) {
        return getValidAgriculture(agricultureService.findCowsProducedAndSlaughteredByArea(area).getAgriculture(), jsonSchema, "application/json");
    }

    @Operation(summary = "Updates a specific agriculture entity")
    @PutMapping(value="{areaCode}/{itemCode}/{elementCode}/{year}", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Void> update(
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
            case "application/xml" -> isValid = isEntityValidXml(agriculture, xmlSchema);
        }
        if (isValid) {
            agricultureService.update(areaCode, itemCode, elementCode, year, agriculture);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Deletes a specific agriculture entity")
    @DeleteMapping(value = "/{areaCode}/{itemCode}/{elementCode}/{year}", produces = {"application/json", "application/xml"})
    public ResponseEntity<Void> delete(
            @PathVariable("areaCode") int areaCode,
            @PathVariable("itemCode") int itemCode,
            @PathVariable("elementCode") int elementCode,
            @PathVariable("year") int year
    ) {
        agricultureService.delete(areaCode, itemCode, elementCode, year);
        return ResponseEntity.ok().build();
    }
}
