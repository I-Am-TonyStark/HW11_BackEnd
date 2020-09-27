package com.mamalimomen.domains;

import com.mamalimomen.base.controller.utilities.InValidDataException;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {

    @Column(nullable = false, updatable = false)
    private String country;

    @Column(nullable = false, updatable = false)
    private String city;

    @Column(nullable = false, updatable = false)
    private String avenue;

    @Column(nullable = false, updatable = false)
    private String postalCode;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws InValidDataException {
        if (!country.matches("\\w{3,}")) {
            throw new InValidDataException("Country");
        }
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws InValidDataException {
        if (!city.matches("\\w{3,}")) {
            throw new InValidDataException("City");
        }
        this.city = city;
    }

    public String getAvenue() {
        return avenue;
    }

    public void setAvenue(String avenue) throws InValidDataException {
        if (!avenue.matches("(\\w\\d*){3,}")) {
            throw new InValidDataException("Avenue");
        }
        this.avenue = avenue;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) throws InValidDataException {
        if (!postalCode.matches("\\d{10}")) {
            throw new InValidDataException("Postal Code");
        }
        this.postalCode = postalCode;
    }
}
