package org.costandino.dataProcessing.domain.co2Emission;

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
@IdClass(Co2EmissionId.class)
@JacksonXmlRootElement(localName = "co2Emission")
public class Co2Emission implements DomainEntity {


    @Id
    @JacksonXmlProperty
    private String countryCode;

    @JacksonXmlProperty
    private String country;

    @Id
    @JacksonXmlProperty
    private int year;

    @JacksonXmlProperty
    private double value;
}
