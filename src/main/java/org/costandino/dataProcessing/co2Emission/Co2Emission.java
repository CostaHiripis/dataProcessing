package org.costandino.dataProcessing.co2Emission;

import jakarta.persistence.*;

@Entity
public class Co2Emission {

    @Id
    @SequenceGenerator(
            name = "co2EmissionSequence",
            sequenceName = "co2EmissionSequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "co2EmissionSequence"
    )
    private long id;

    @Column
    private String countryCode;

    @Column
    private String country;

    @Column
    private int year;

    @Column
    private double value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
