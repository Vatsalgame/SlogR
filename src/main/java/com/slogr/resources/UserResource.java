package com.slogr.resources;

import com.slogr.core.User;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 * All the stuff regarding the User and its interactions
 *
 * @author vatsalgame
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    @GET
    @Path("/basic")
    public Response getBasicUser() {
        return Response.ok(new User(9L, "basic user", "basic password")).build();
    }

}
