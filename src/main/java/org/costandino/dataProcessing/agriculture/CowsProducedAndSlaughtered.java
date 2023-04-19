package org.costandino.dataProcessing.agriculture;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import org.costandino.dataProcessing.DomainEntity;

@Data
@JacksonXmlRootElement
public class CowsProducedAndSlaughtered implements DomainEntity {

    @JacksonXmlElementWrapper(useWrapping = false)
    private int value;

}
