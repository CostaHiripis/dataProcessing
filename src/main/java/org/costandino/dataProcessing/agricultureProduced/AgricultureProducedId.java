package org.costandino.dataProcessing.agricultureProduced;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgricultureProducedId implements Serializable {

    private int areaCode;
    private int itemCode;
    private int elementCode;
    private int year;
}
