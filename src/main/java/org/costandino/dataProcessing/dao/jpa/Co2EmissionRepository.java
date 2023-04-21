package org.costandino.dataProcessing.dao.jpa;

import org.costandino.dataProcessing.domain.co2Emission.Co2Emission;
import org.costandino.dataProcessing.domain.co2Emission.Co2EmissionId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Co2EmissionRepository extends JpaRepository<Co2Emission, Co2EmissionId> {

    List<Co2Emission> findByYear(int year);

}
