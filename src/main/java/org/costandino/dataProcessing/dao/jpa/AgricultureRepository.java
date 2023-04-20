package org.costandino.dataProcessing.dao.jpa;

import org.costandino.dataProcessing.domain.Agriculture.Agriculture;
import org.costandino.dataProcessing.domain.Agriculture.AgricultureId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AgricultureRepository extends JpaRepository<Agriculture, AgricultureId> {

    List<Agriculture> findByAreaAndItemCodeAndElementCode(String area, int itemCode, int elementCode);

}
