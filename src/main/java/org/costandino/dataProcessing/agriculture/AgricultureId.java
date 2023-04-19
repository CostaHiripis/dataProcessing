package org.costandino.dataProcessing.agriculture;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AgricultureId implements Serializable {

    private int areaCode;
    private int itemCode;
    private int elementCode;
    private int year;
}
