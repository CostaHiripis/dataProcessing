package org.costandino.dataProcessing.dao.jpa;

import org.costandino.dataProcessing.domain.globalSeaLevel.GlobalSeaLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GlobalSeaLevelRepository extends JpaRepository<GlobalSeaLevel, Integer> {

}
