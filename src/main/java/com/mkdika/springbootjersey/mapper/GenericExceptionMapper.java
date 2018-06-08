
package com.mkdika.springbootjersey.mapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author Maikel Chandika (mkdika@gmail.com)
 */
@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable e) {
        return Response.serverError().entity(e.getMessage()).build();
    }
    
}
