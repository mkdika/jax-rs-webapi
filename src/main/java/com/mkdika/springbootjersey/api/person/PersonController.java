package com.mkdika.springbootjersey.api.person;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
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

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPersons() {
        List<Person> list = (List<Person>) personRepository.findAll();
        if (list.size() > 0) {
            return Response.status(Response.Status.OK.getStatusCode()).entity(list).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
        }
    }
    
    @GET
    @Path("/find")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findPersonByName(@QueryParam("name") String name) {
        List<Person> list = personRepository.findByNameContainingAllIgnoreCase(name);
        if (list.size() > 0) {
            return Response.status(Response.Status.OK.getStatusCode()).entity(list).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getPersonById(@PathParam("id") Integer id) {
        Person person = personRepository.findOne(id);
        if (person != null) {
            return Response.status(Response.Status.OK.getStatusCode()).entity(person).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deletePersonById(@PathParam("id") Integer id) {
        Person person = personRepository.findOne(id);
        if (person != null) {
            personRepository.delete(person);
            return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deletePersons() {
        personRepository.deleteAll();
        return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response insertPerson(Person person) {
        person = personRepository.save(new Person(person.getName(),person.getBirthDate()));
        return Response.status(Response.Status.CREATED.getStatusCode()).entity(person).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updatePerson(Person person, @PathParam("id") Integer id) {
        Person p = personRepository.findOne(id);
        if (p != null) {
            p = personRepository.save(person);
            return Response.status(Response.Status.OK.getStatusCode()).entity(p).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
    }
}
