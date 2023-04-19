package org.costandino.dataProcessing.agriculture;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import jakarta.persistence.*;
import lombok.*;
import org.costandino.dataProcessing.DomainEntity;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(AgricultureId.class)
@JacksonXmlRootElement(localName = "Agriculture")
public class Agriculture implements DomainEntity {

    @Id
    @JacksonXmlProperty
    private int areaCode;

    @Id
    @JacksonXmlProperty
    private int itemCode;

    @Id
    @JacksonXmlProperty
    private int year;

    @Id
    @JacksonXmlProperty
    private int elementCode;

    @JacksonXmlProperty
    private String item;

    @JacksonXmlProperty
    private String area;

    @JacksonXmlProperty
    private String element;

    @JacksonXmlProperty
    private String unit;

    @JacksonXmlProperty
    private int value;

    @JacksonXmlProperty
    private String flag;

}
