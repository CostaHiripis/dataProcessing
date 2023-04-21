package org.costandino.dataProcessing.dao.jpa;

import org.costandino.dataProcessing.domain.agriculture.Agriculture;
import org.costandino.dataProcessing.domain.agriculture.AgricultureId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AgricultureRepository extends JpaRepository<Agriculture, AgricultureId> {

    List<Agriculture> findByYearAndItemCodeAndElementCode(int year, int itemCode, int elementCode);

}
