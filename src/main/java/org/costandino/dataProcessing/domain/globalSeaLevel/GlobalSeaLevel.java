package org.costandino.dataProcessing.domain.globalSeaLevel;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.costandino.dataProcessing.domain.DomainEntity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JacksonXmlRootElement(localName = "globalSeaLevel")
public class GlobalSeaLevel implements DomainEntity {

    @Id
    @JacksonXmlProperty
    private int year;

    @JacksonXmlProperty
    private double mm;

}
