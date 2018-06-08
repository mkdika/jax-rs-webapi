
package com.mkdika.springbootjersey.person;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
@Component
@Path("/persons")
public class PersonController {
    
    private final PersonRepository personRepository;
    
    private final AddressRepository addressRepository;

    @Autowired
    public PersonController(PersonRepository personRepository,
                            AddressRepository addressRepository) {
        this.personRepository = personRepository;
        this.addressRepository = addressRepository;
    }
    
    @GET
    @Produces({ MediaType.APPLICATION_JSON})
    public Response getPersons() {
        List<Person> list = (List<Person>) personRepository.findAll();
        if (list.size() > 0) {
            return Response.status(Response.Status.OK.getStatusCode()).entity(list).build();
        }else {
            return Response.status(Response.Status.NO_CONTENT.getStatusCode()).entity(list).build();
        }
    }
    
    
    
    
}
