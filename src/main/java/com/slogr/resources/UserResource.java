package com.slogr.resources;

import com.mongodb.client.MongoCollection;
import com.slogr.core.User;
import com.slogr.db.CrudUser;
import com.slogr.db.MongoDriver;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

/**
 * All the stuff regarding the User and its interactions
 *
 * @author vatsalgame
 */
@Path("/user")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserResource.class);

    private static MongoDriver driver = new MongoDriver();

    private static CrudUser userOps = new CrudUser();

    @GET
    @Path("/basic")
    public Response getBasicUser() {
        LOGGER.info("basic user");

        return Response.ok(new User("basic user", "basic password")).build();
    }

    @POST
    @Path("/addNewUser")
    public Response addNewUser(@Valid User user) {
        LOGGER.info("User email: " + user.getEmail());
        LOGGER.info("User password: " + user.getPassword());

        // Check if user exits, and react accordingly.
        if (userOps.checkUserExistence(user)) {
            // Throw a conflict error because user already exists
            return Response.status(Response.Status.CONFLICT).build();
        } else {
            // Create the user
            userOps.createUser(user);
            return Response.ok("User successfully added").build();
        }
    }

    @POST
    @Path("/login")
    public Response login(@Valid User user) {
        LOGGER.info("User email: " + user.getEmail());
        LOGGER.info("User password: " + user.getPassword());

        if (userOps.loginUser(user)) {
            return Response.ok("User credentials valid").build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("/getAllUsers")
    public Response getAllUsers() {
        List<User> users = userOps.getAllUsers();

        return Response.ok(users).build();
    }

    @GET
    @Path("/getTestCollection")
    public Response getTestCollection() {
        MongoCollection<Document> collection = driver.getTestDataCollection();

        return Response.ok(collection).build();
    }

    @GET
    @Path("/getTestData")
    public Response getTestData() {
        return Response.ok(driver.getTestData()).build();
    }

}
