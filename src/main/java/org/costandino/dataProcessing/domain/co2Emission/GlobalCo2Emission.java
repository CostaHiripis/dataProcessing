package org.costandino.dataProcessing.domain.co2Emission;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.costandino.dataProcessing.domain.DomainEntity;

@Data
@AllArgsConstructor
@JacksonXmlRootElement(localName = "globalCo2Emission")
public class GlobalCo2Emission implements DomainEntity {

    private double emission;
}
