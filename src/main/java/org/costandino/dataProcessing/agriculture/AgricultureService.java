package org.costandino.dataProcessing.agriculture;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AgricultureService {

    private final AgricultureRepository agricultureRepository;

    public void save(Agriculture agriculture) {
        agricultureRepository.save(agriculture);
    }

    public Agriculture findAgricultureByAreaCodeItemCodeElementCodeAndYear(int areaCode, int itemCode, int elementCode, int year) {
        AgricultureId agricultureId = new AgricultureId(areaCode, itemCode, elementCode, year);
        return agricultureRepository.findById(agricultureId).orElseThrow(NullPointerException::new);
    }


    public CowsProducedAndSlaughtered findCowsProducedAndSlaughteredEveryYearPerArea(String area, int year)  {
        var cowsProducedAndSlaughtered = new CowsProducedAndSlaughtered();
        cowsProducedAndSlaughtered.setValue(agricultureRepository.findAgricultureByAreaAndItemCodeAndElementCodeAndYear(area, 867, 5320, year).getValue());
        return cowsProducedAndSlaughtered;
    }

    public Agricultures findAll() {
        var agricultures = (List<Agriculture>) agricultureRepository.findAll();
        var agricultureSearchResults = new Agricultures();
        agricultureSearchResults.setAgriculture(agricultures);
        return agricultureSearchResults;
    }

    public void delete(int areaCode, int itemCode, int elementCode, int year) {
        AgricultureId agricultureId = new AgricultureId(areaCode, itemCode, elementCode, year);
        agricultureRepository.deleteById(agricultureId);
    }

    public void update(int areaCode, int itemCode, int elementCode, int year, Agriculture agriculture) {

        //Checks if the agriculture exists
        findAgricultureByAreaCodeItemCodeElementCodeAndYear(areaCode, itemCode, elementCode, year);
        agricultureRepository.save(agriculture);
    }
}
