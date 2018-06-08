package com.mkdika.springbootjersey.api.person;

import com.mkdika.springbootjersey.api.address.Address;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id") // to prevent Jackson recursion serialization
public class Person implements Serializable {

    @Id
    @GeneratedValue
    Integer id;

    String name;

    @Column(name = "birth_date")
    Date birthDate;

    @OneToOne(mappedBy = "person", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER, optional = false)
    Address address;

    public Person(String name, Date birthDate) {
        this.name = name;
        this.birthDate = birthDate;
    }

}
