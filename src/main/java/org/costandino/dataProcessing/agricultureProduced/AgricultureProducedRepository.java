package org.costandino.dataProcessing.agricultureProduced;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AgricultureProducedRepository extends JpaRepository<AgricultureProduced, AgricultureProducedId> {

    Optional<AgricultureProduced> findAgricultureProducedByAreaAndYearAndItem(String area, int year, String item);
}
