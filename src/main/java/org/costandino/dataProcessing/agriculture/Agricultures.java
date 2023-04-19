package org.costandino.dataProcessing.agriculture;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import org.costandino.dataProcessing.DomainEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@JacksonXmlRootElement
public class Agricultures implements DomainEntity {

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Agriculture")
    private List<Agriculture> agriculture = new ArrayList<>();
}
