package org.costandino.dataProcessing.domain.co2Emission;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import org.costandino.dataProcessing.domain.DomainEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "co2Emissions")
public class Co2Emissions implements DomainEntity {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Co2Emission> co2Emission = new ArrayList<>();
}
