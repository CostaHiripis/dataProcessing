package org.costandino.dataProcessing.domain.globalSeaLevel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;
import org.costandino.dataProcessing.domain.DomainEntity;

import java.util.ArrayList;
import java.util.List;

@Data
@JacksonXmlRootElement(localName = "globalSeaLevels")
public class GlobalSeaLevels implements DomainEntity {

    @JacksonXmlElementWrapper(useWrapping = false)
    private List<GlobalSeaLevel> globalSeaLevel = new ArrayList<>();
}
