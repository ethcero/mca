package es.ethcero.mca.practica2.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Address {

    private String street;
    private String number;
    private String city;

}
