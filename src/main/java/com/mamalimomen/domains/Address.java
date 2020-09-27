package com.mamalimomen.domains;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String country;
    private String city;
    private String avenue;
    private String postalCode;
}
