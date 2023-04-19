package org.costandino.dataProcessing.agriculture;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

@Data
@JacksonXmlRootElement
public class CowsProducedAndSlaughtered {

    @JacksonXmlElementWrapper(useWrapping = false)
    private int value;

}
