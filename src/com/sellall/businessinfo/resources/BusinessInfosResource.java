package com.sellall.businessinfo.resources;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.FormParam;

import com.sellall.businessinfo.entities.BusinessInfo;
import com.sellall.businessinfo.services.BusinessListService;

// Will map the resource to the URL todos
@Path("/businessinfolist")
public class BusinessInfosResource {

	// Allows to insert contextual objects into the class, 
	// e.g. ServletContext, Request, Response, UriInfo
	@Context
	UriInfo uriInfo;
	@Context
	Request request;

	@GET
	@Produces({MediaType.APPLICATION_JSON})
	public List<BusinessInfo> getBusinessList() throws Exception {
		System.out.println("Request parameter" + request);
		List<BusinessInfo> businessList= new ArrayList<BusinessInfo>();

		BusinessListService bls = new BusinessListService();
		businessList.addAll(bls.getBusinessListFromDatabase());
		return businessList; 
		}
}