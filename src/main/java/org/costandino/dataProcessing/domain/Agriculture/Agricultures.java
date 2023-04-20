package org.costandino.dataProcessing.domain.Agriculture;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import org.costandino.dataProcessing.domain.DomainEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "agricultures")
public class Agricultures implements DomainEntity {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Agriculture> agriculture = new ArrayList<>();
}
