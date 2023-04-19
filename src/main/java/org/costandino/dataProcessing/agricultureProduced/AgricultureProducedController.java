package org.costandino.dataProcessing.agricultureProduced;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/agriculture-produced")
@RequiredArgsConstructor
@Tag(name="agriculture", description = "The agriculture per country per year")
public class AgricultureProducedController {

    private final AgricultureProducedService agricultureProducedService;

    @Operation(summary = "Create an agriculture entry")
    @PostMapping(
    consumes = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ResponseEntity<AgricultureProduced> save(
            @RequestBody AgricultureProduced agricultureProduced
    ) {
        return ResponseEntity.ok(agricultureProducedService.save(agricultureProduced));
    }

    @GetMapping
    public ResponseEntity<List<AgricultureProduced>> findAll() {
        return ResponseEntity.ok(agricultureProducedService.findAll());
    }

    @GetMapping("/{area}/{year}/{item}")
    public ResponseEntity<AgricultureProduced> getAgricultureProduced(
            @PathVariable("area") String area,
            @PathVariable("year") int year,
            @PathVariable("item") String item
    ) {
        return ResponseEntity.ok(agricultureProducedService.findByAreaYearAndItem(area, year, item));
    }

//    @PutMapping("/{area}/{year}/{item}")
//    public ResponseEntity<AgricultureProduced> update(
//            @PathVariable("area") String area,
//            @PathVariable("year") int year,
//            @PathVariable("item") String item,
//            @RequestBody AgricultureProduced agricultureProduced
//    ) {
//        try {
//            agricultureProducedService.get
//        }
//    }

    @DeleteMapping("/{areaCode}/{itemCode}/{elementCode}/{year}")
    public ResponseEntity<Void> delete(
            @PathVariable("areaCode") int areaCode,
            @PathVariable("itemCode") int itemCode,
            @PathVariable("elementCode") int elementCode,
            @PathVariable("year") int year
    ) {
        agricultureProducedService.delete(areaCode, itemCode, elementCode, year);
        return ResponseEntity.accepted().build();
    }
}
