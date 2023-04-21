package org.costandino.dataProcessing.domain.agriculture;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.costandino.dataProcessing.domain.DomainEntity;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(localName = "globalCowsProducedAndSlaughtered")
public class GlobalCowsProducedAndSlaughtered implements DomainEntity {

    private double headCount;
}
