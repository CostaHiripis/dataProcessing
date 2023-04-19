package org.costandino.dataProcessing.agriculture;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.File;


@RestController
@RequestMapping("api/v1/agriculture")
@RequiredArgsConstructor
public class AgricultureController {

    private final AgricultureService agricultureService;

    @PostMapping(
    consumes = {"application/json", "application/xml"})
    public ResponseEntity<Void> save(
            @RequestBody Agriculture agriculture
    ) {
        agricultureService.save(agriculture);
        return ResponseEntity.ok().build();

    }

    @GetMapping(value = "/json", produces = {"application/json"})
    public Agricultures findAllJson() {
        return agricultureService.findAll();
    }

    @GetMapping(value = "/xml", produces= {"application/xml"})
    public Agricultures findAllXml() {
            return agricultureService.findAll();
    }

    @GetMapping(value = "/json/{areaCode}/{itemCode}/{elementCode}/{year}", produces= {"application/json"})
    public Agriculture getAgricultureJson(
            @PathVariable("areaCode") int areaCode,
            @PathVariable("itemCode") int itemCode,
            @PathVariable("elementCode") int elementCode,
            @PathVariable("year") int year
    ) {
        return agricultureService.findAgricultureByAreaCodeItemCodeElementCodeAndYear(areaCode, itemCode, elementCode, year);
    }

    @GetMapping(value = "/xml/{areaCode}/{itemCode}/{elementCode}/{year}", produces= {"application/xml"})
    public Agriculture getAgricultureXml(
            @PathVariable("areaCode") int areaCode,
            @PathVariable("itemCode") int itemCode,
            @PathVariable("elementCode") int elementCode,
            @PathVariable("year") int year
    ) {
        return agricultureService.findAgricultureByAreaCodeItemCodeElementCodeAndYear(areaCode, itemCode, elementCode, year);
    }

    @GetMapping(value="json/{area}/{year}", produces = {"application/json"})
    public CowsProducedAndSlaughtered getCowsProducedAndSlaughteredPerYearByCountryJson(
            @PathVariable("area") String area,
            @PathVariable("year") int year
    ) {
        return agricultureService.findCowsProducedAndSlaughteredEveryYearPerArea(area, year);
    }

    @GetMapping(value="xml/{area}/{year}", produces = {"application/xml"})
    public CowsProducedAndSlaughtered getCowsProducedAndSlaughteredPerYearByCountryXml(
            @PathVariable("area") String area,
            @PathVariable("year") int year
    ) {
        return agricultureService.findCowsProducedAndSlaughteredEveryYearPerArea(area, year);
    }

    @PutMapping(value="{areaCode}/{itemCode}/{elementCode}/{year}", consumes = {"application/xml", "application/json"})
    public ResponseEntity<Void> update(
            @PathVariable("areaCode") int areaCode,
            @PathVariable("itemCode") int itemCode,
            @PathVariable("elementCode") int elementCode,
            @PathVariable("year") int year,
            @RequestBody Agriculture agriculture
    ) {
        agricultureService.update(areaCode, itemCode, elementCode, year, agriculture);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{areaCode}/{itemCode}/{elementCode}/{year}")
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
