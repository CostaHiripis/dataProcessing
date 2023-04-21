package org.costandino.dataProcessing.services;

import lombok.RequiredArgsConstructor;
import org.costandino.dataProcessing.dao.jpa.GlobalSeaLevelRepository;
import org.costandino.dataProcessing.domain.globalSeaLevel.GlobalSeaLevel;
import org.costandino.dataProcessing.domain.globalSeaLevel.GlobalSeaLevels;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GlobalSeaLevelService {

    private final GlobalSeaLevelRepository globalSeaLevelRepository;

    public void save(GlobalSeaLevel globalSeaLevel) {
        globalSeaLevelRepository.save(globalSeaLevel);
    }

    public GlobalSeaLevel findGlobalSeaLevelById(int year) {
        return globalSeaLevelRepository.findById(year).orElseThrow(NullPointerException::new);
    }

    public GlobalSeaLevels findAll() {
        var globalSeaLevels = (List<GlobalSeaLevel>) globalSeaLevelRepository.findAll();
        var globalSeaLevelsSearchResults = new GlobalSeaLevels();
        globalSeaLevelsSearchResults.setGlobalSeaLevel(globalSeaLevels);
        return globalSeaLevelsSearchResults;
    }

    public void delete(int year) {
        findGlobalSeaLevelById(year);
        globalSeaLevelRepository.deleteById(year);
    }

    public void update(int year, GlobalSeaLevel globalSeaLevel) {
        findGlobalSeaLevelById(year);
        globalSeaLevelRepository.save(globalSeaLevel);
    }

}
