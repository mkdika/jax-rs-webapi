package com.mkdika.springbootjersey.person;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
@Entity
@Getter
@Setter
public class Address implements Serializable {

    @Id
    @GeneratedValue
    Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "person_id")
    Person person;

    String address;

    String city;

    @Column(name = "zip_code")
    String zipCode;

}
