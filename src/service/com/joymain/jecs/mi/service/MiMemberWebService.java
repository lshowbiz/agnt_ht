package com.joymain.jecs.mi.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;

import com.joymain.jecs.mi.model.MiStatusResponse;




@Path(value = "/miMember")
public interface MiMemberWebService
{

//    @GET
//    @Path(value = "/changeCustomerLevel")
//    MiStatusResponse getChangeCustomerLevel(@QueryParam("userCode") String userCode,@QueryParam("customerLevel") String customerLevel);
    @GET
    @Path(value = "/changeCustomerLevelByAmount")
	public MiStatusResponse changeCustomerLevelByAmount(@QueryParam("userCode") String userCode,@QueryParam("amount") String amount);
}