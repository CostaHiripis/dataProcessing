package org.costandino.dataProcessing.agricultureProduced;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@IdClass(AgricultureProducedId.class)
public class AgricultureProduced {

    @Id
    private int areaCode;

    @Id
    private int itemCode;

    private String item;

    private String area;

    @Id
    private int year;

    @Id
    private int elementCode;

    private String element;

    private String unit;

    private int value;

    private String flag;

}
