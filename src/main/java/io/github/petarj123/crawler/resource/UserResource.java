package io.github.petarj123.crawler.resource;

import io.github.petarj123.common.dto.UserDTO;
import io.github.petarj123.crawler.service.UserService;
import io.github.petarj123.domain.entity.User;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/users")
public class UserResource {
    @Inject
    UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<User> getAll() {
        return userService.getAll();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getById(@PathParam("id") Long id) {
        return userService.getById(id);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User createUser(UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public User updateUser(Long id, UserDTO userDTO) throws NoSuchFieldException, IllegalAccessException {
        return userService.updateUser(id, userDTO);
    }
}
