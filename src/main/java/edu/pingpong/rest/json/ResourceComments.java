package edu.pingpong.rest.json;

import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.pingpong.rest.json.domain.Comments;
import edu.pingpong.rest.json.service.ServiceComments;

@Path("/comments")
public class ResourceComments {

    @Inject 
    ServiceComments service;

    public ResourceComments() {}

    @GET
    // @Path("/hello")
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Welcome to the comments Api";
    }

    @GET
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Comments> list() {
        return service.list();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Set<Comments> add(@Valid Comments comment) {
        service.add(comment);
        return this.list();
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(@PathParam("name") String name) {
        Optional<Comments> comment = service.getComment(name);
        return comment.isPresent()?
        Response.status(Response.Status.OK).entity(comment.get()).build() :
        Response.status(Response.Status.NOT_FOUND).build();
    }
}