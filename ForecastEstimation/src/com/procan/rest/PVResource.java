package com.procan.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

public class PVResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	PvService pvService;

	public PVResource(String id) {

		this.id = id;
		pvService = new PvService();
	}

	// resultat get
	@GET
	@Produces(MediaType.TEXT_XML)
	public String getPVs() {
		return pvService.getPv(id);
	}

}