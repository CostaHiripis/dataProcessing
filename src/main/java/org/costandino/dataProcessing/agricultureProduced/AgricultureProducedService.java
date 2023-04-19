package org.costandino.dataProcessing.agricultureProduced;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgricultureProducedService {

    private final AgricultureProducedRepository agricultureProducedRepository;

    public AgricultureProduced save(AgricultureProduced agricultureProduced){
        return agricultureProducedRepository.save(agricultureProduced);
    }

    public AgricultureProduced findByAreaYearAndItem(String area, int year, String item) {
        return agricultureProducedRepository.findAgricultureProducedByAreaAndYearAndItem(area, year, item).
                orElse(null);
    }

    public List<AgricultureProduced> findAll() {
        return agricultureProducedRepository.findAll();
    }

    public void delete(int areaCode, int itemCode, int elementCode, int year) {
        AgricultureProducedId agricultureProducedId = new AgricultureProducedId(areaCode, itemCode, elementCode, year);
        agricultureProducedRepository.deleteById(agricultureProducedId);
    }
}
