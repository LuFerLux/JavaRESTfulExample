/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.luferlux.restful;

import com.google.gson.Gson;
import com.luferlux.database.entity.User;
import com.luferlux.database.service.UserService;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author LuFerLux
 */
@Path("users")
public class UserResource
{

    private final UserService service;

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ServertestResource
     */
    public UserResource()
    {
        service = new UserService();
    }

    @GET
    @Path("all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers()
    {
        try
        {
            List<User> users = service.getAll();

            String json = new Gson().toJson(users);

            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        catch (Exception ex)
        {
            System.out.println("Error getting users: " + ex.toString());

            return Response.status(Response.Status.SEE_OTHER).entity("Error: " + ex.toString()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(User user)
    {
        try
        {
            System.out.println("adding user: " + user);

            service.create(user);

            user = service.get(user);

            System.out.println("added user: " + user);

            String json = "{\"id\":\"" + user.getId() + "\"}";

            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        catch (Exception ex)
        {
            System.out.println("Error adding user: " + ex.toString());

            return Response.status(Response.Status.SEE_OTHER).entity("Error: " + ex.toString()).build();
        }
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(User user, @PathParam("id") int id)
    {
        try
        {
            System.out.println("updating user, userId: " + id + ", user: " + user);

            user.setId(id);

            service.update(user);

            System.out.println("updated user: " + user);
            
            String json = "{\"id\":\"" + user.getId() + "\"}";

            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        catch (Exception ex)
        {
            System.out.println("Error updated user: " + ex.toString());
            
            return Response.status(Response.Status.SEE_OTHER).entity("Error: " + ex.toString()).build();
        }
    }

    @DELETE
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteUser(@PathParam("id") int id)
    {
        try
        {
            System.out.println("deleting userId: " + id);

            User user = new User();

            user.setId(id);

            service.delete(user);

            String json = "{\"id\":\"" + user.getId() + "\"}";

            System.out.println("user deleted: " + id);

            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        catch (Exception ex)
        {
            System.out.println("Error deleting userId: " + id);

            return Response.status(Response.Status.SEE_OTHER).entity("Error: " + ex.toString()).build();
        }
    }
}
