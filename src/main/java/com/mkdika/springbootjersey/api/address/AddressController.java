package com.mkdika.springbootjersey.api.address;

import com.mkdika.springbootjersey.api.person.Person;
import com.mkdika.springbootjersey.api.person.PersonRepository;
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
@Path("/persons/address")
public class AddressController {

    private AddressRepository addressRepository;

    private final PersonRepository personRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository,
            PersonRepository personRepository) {
        this.addressRepository = addressRepository;
        this.personRepository = personRepository;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAddresses() {
        List<Address> list = (List<Address>) addressRepository.findAll();
        if (list.size() > 0) {
            return Response.status(Response.Status.OK.getStatusCode()).entity(list).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
        }
    }

    @GET
    @Path("/find")
    @Produces({MediaType.APPLICATION_JSON})
    public Response findAddressByAddress(@QueryParam("address") String address) {
        List<Address> list = addressRepository.findByAddressContainingAllIgnoreCase(address);
        if (list.size() > 0) {
            return Response.status(Response.Status.OK.getStatusCode()).entity(list).build();
        } else {
            return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces({MediaType.APPLICATION_JSON})
    public Response getAddressById(@PathParam("id") Integer id) {
        Address address = addressRepository.findOne(id);
        if (address != null) {
            return Response.status(Response.Status.OK.getStatusCode()).entity(address).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteAddressById(@PathParam("id") Integer id) {
        Address address = addressRepository.findOne(id);
        if (address != null) {
            addressRepository.delete(address);
            return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
    }

    @DELETE
    @Consumes({MediaType.APPLICATION_JSON})
    public Response deleteAddress() {
        addressRepository.deleteAll();
        return Response.status(Response.Status.NO_CONTENT.getStatusCode()).build();
    }

    @POST
    @Path("/persons/{personid}/address")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response insertPerson(Address address, @PathParam("personid") Integer personId) {
        Person person = personRepository.findOne(personId);
        if (person != null) {
            Address a = new Address();
            a.setPerson(person);
            a.setAddress(address.getAddress());
            a.setCity(address.getCity());
            a.setZipCode(address.getZipCode());
            address = addressRepository.save(a);
            return Response.status(Response.Status.CREATED.getStatusCode()).entity(address).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
    }

    @PUT
    @Path("/persons/{personid}/address/{id}")
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response updatePerson(Address address,
            @PathParam("personid") Integer personId,
            @PathParam("id") Integer id) {
        Person person = personRepository.findOne(personId);
        Address a = addressRepository.findOne(id);
        if (person != null && a != null) {
            address.setPerson(person);
            a = addressRepository.save(address);
            return Response.status(Response.Status.OK.getStatusCode()).entity(a).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND.getStatusCode()).build();
        }
    }
}
