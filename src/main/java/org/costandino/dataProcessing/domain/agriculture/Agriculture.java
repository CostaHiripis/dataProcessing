package org.costandino.dataProcessing.domain.agriculture;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.*;
import org.costandino.dataProcessing.domain.DomainEntity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(AgricultureId.class)
@JacksonXmlRootElement(localName = "agriculture")
public class Agriculture implements DomainEntity {

    @Id
    @JacksonXmlProperty
    private int areaCode;

    @JacksonXmlProperty
    private String area;

    @Id
    @JacksonXmlProperty
    private int itemCode;

    @JacksonXmlProperty
    private String item;

    @Id
    @JacksonXmlProperty
    private int elementCode;

    @JacksonXmlProperty
    private String element;

    @Id
    @JacksonXmlProperty
    private int year;

    @JacksonXmlProperty
    private String unit;

    @JacksonXmlProperty
    private int value;

    @JacksonXmlProperty
    private String flag;

}
