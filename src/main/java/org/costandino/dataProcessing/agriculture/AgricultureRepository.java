package org.costandino.dataProcessing.agriculture;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AgricultureRepository extends JpaRepository<Agriculture, AgricultureId> {

    Agriculture findAgricultureByAreaAndItemCodeAndElementCodeAndYear(String area, int itemCode, int elementCode, int year);

}
