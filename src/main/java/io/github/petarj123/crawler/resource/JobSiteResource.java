package io.github.petarj123.crawler.resource;

import io.github.petarj123.common.dto.JobSiteDTO;
import io.github.petarj123.crawler.service.JobSiteService;
import io.github.petarj123.domain.entity.JobSite;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/job_sites")
public class JobSiteResource {
    @Inject
    Validator validator;

    @Inject
    JobSiteService jobSiteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        List<JobSite> jobSites = jobSiteService.getAll();
        return Response.ok(jobSites).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        JobSite jobSite = jobSiteService.getById(id);
        return Response.ok(jobSite).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createJobSite(JobSiteDTO dto) {
        JobSite jobSite = jobSiteService.createJobSite(dto);
        return Response.ok(jobSite).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateJobSite(@PathParam("id") int id, JobSiteDTO jobSiteDTO) throws NoSuchFieldException, IllegalAccessException {
        JobSite jobSite = jobSiteService.updateJobSite(id, jobSiteDTO);
        return Response.ok(jobSite).build();
    }
}
