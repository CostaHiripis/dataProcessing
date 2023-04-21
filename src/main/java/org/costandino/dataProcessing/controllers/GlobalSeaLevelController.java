package org.costandino.dataProcessing.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.costandino.dataProcessing.domain.globalSeaLevel.GlobalSeaLevel;
import org.costandino.dataProcessing.domain.globalSeaLevel.GlobalSeaLevels;
import org.costandino.dataProcessing.services.GlobalSeaLevelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.Map;

@RestController
@RequestMapping("globalSeaLevel")
@RequiredArgsConstructor
@Tag(name = "GlobalSeaLevel", description = "The globalSeaLevel endpoints")
public class GlobalSeaLevelController extends GlobalRestHandler {

    private final File jsonSchema = new File("src/main/resources/schema/seaLevel/globalSeaLevel_schema.json");
    private final File xmlSchema = new File("src/main/resources/schema/seaLevel/globalSeaLevel_schema.xsd");

    private final GlobalSeaLevelService globalSeaLevelService;

    @Operation(summary = "Creates a seaLevel entity")
    @PostMapping(
            consumes = {"application/json", "application/xml"})
    public ResponseEntity<?> save(
            @RequestHeader Map<String, String> headers,
            @RequestBody GlobalSeaLevel globalSeaLevel
    ) {
        boolean isValid = false;
        switch (headers.get("content-type")) {
            case "application/json" -> isValid = isEntityValidJson(globalSeaLevel, jsonSchema);
            case "application/xml" -> isValid = isEntityValidXml(globalSeaLevel, xmlSchema, "GlobalSeaLevel");
        }
        if (isValid) {
            globalSeaLevelService.save(globalSeaLevel);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Gets all the seaLevel entities in xml format")
    @GetMapping(value = "/xml", produces= {"application/xml"})
    public GlobalSeaLevels findAllXml() {
        return getValidSeaLevel(globalSeaLevelService.findAll().getGlobalSeaLevel(), xmlSchema, "application/xml");
    }

    @Operation(summary = "Gets all the seaLevel entities in json format")
    @GetMapping(value = "/json", produces= {"application/json"})
    public GlobalSeaLevels findAllJson() {
        return getValidSeaLevel(globalSeaLevelService.findAll().getGlobalSeaLevel(), jsonSchema, "application/json");
    }

    @Operation(summary = "Gets a specific seaLevel entity in xml format")
    @GetMapping(value = "/xml/{year}", produces= {"application/xml"})
    public GlobalSeaLevel getGlobalSeaLevelXml(
            @PathVariable("year") int year
    ) {
        GlobalSeaLevel globalSeaLevel = globalSeaLevelService.findGlobalSeaLevelById(year);
        isEntityValidXml(globalSeaLevel, xmlSchema, "GlobalSeaLevel");
        return globalSeaLevel;
    }

    @Operation(summary = "Gets a specific seaLevel entity in json format")
    @GetMapping(value = "/json/{year}", produces= {"application/json"})
    public GlobalSeaLevel getGlobalSeaLevelJson(
            @PathVariable("year") int year
    ) {
        GlobalSeaLevel globalSeaLevel = globalSeaLevelService.findGlobalSeaLevelById(year);
        isEntityValidJson(globalSeaLevel, jsonSchema);
        return globalSeaLevel;
    }

    @Operation(summary = "Updates a specific seaLevel entity")
    @PutMapping(value="/{year}", consumes = {"application/xml", "application/json"})
    public ResponseEntity<?> update(
            @PathVariable("year") int year,
            @RequestBody GlobalSeaLevel globalSeaLevel,
            @RequestHeader Map<String, String> headers
    ) {

        boolean isValid = false;
        switch (headers.get("content-type")) {
            case "application/json" -> isValid = isEntityValidJson(globalSeaLevel, jsonSchema);
            case "application/xml" -> isValid = isEntityValidXml(globalSeaLevel, xmlSchema, "GlobalSeaLevel");
        }
        if (isValid) {
            globalSeaLevelService.update(year, globalSeaLevel);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = "Deletes a specific seaLevel entity")
    @DeleteMapping(value = "/{year}")
    public ResponseEntity<?> delete(
            @PathVariable("year") int year
    ) {
        globalSeaLevelService.delete(year);
        return ResponseEntity.ok().build();
    }

}
