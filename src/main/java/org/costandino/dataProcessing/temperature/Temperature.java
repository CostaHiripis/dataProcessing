package org.costandino.dataProcessing.temperature;

import jakarta.persistence.*;

@Entity
public class Temperature {

    @Id
    @SequenceGenerator(
            name = "temperatureSequence",
            sequenceName = "temperatureSequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "temperatureSequence"
    )
    private long id;

    @Column
    private int year;

    @Column
    private double averageTemperature;

    @Column
    private double averageTemperatureUncertainty;

    @Column
    private String country;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getAverageTemperature() {
        return averageTemperature;
    }

    public void setAverageTemperature(double averageTemperature) {
        this.averageTemperature = averageTemperature;
    }

    public double getAverageTemperatureUncertainty() {
        return averageTemperatureUncertainty;
    }

    public void setAverageTemperatureUncertainty(double averageTemperatureUncertainty) {
        this.averageTemperatureUncertainty = averageTemperatureUncertainty;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
