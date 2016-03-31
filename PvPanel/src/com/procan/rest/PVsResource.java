package com.procan.rest;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

@Path("/pvs")
public class PVsResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	PvService pvService;

	public PVsResource() {
		pvService = new PvService();
	}

	// resultat get
	@GET
	@Produces(MediaType.TEXT_XML)
	public String getPVs() {
		return pvService.getPVList();
	}

	
	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createAnimal(@FormParam("ts") long ts,
			@FormParam("device_id") String device_id,
			@FormParam("production") double production,
			@Context HttpServletResponse servletResponse) throws IOException {
		
		pvService.AddPV(ts, device_id, production);
		servletResponse.sendRedirect("./pvs/");
	}

	@Path("{pv}")
	public PVResource getAnimal(@PathParam("pv") String id) {
		return new PVResource(id);
	}

}