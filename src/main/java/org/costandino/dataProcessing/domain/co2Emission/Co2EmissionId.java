package org.costandino.dataProcessing.domain.co2Emission;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Co2EmissionId implements Serializable {

    private String countryCode;
    private int year;
}
