package org.iitwforce.restassured.restassured;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.response.Response;

public class PetStoreExampleNP extends RestLibrary{
	@Test(priority=1)
	public void createAPet() throws Exception
	{
		String requestURL="https://petstore.swagger.io/v2/pet";
		String payloadString= readPayload("AddPetPayload.txt").replace("$randString$","dogggg");

		Response resp = postServiceResponse(requestURL,payloadString);
		
		System.out.println(resp.getStatusCode());
		
		System.out.println(resp.asString());
		
	    Integer idDetails = com.jayway.jsonpath.JsonPath.read(resp.asString(), "$.id");
		
		System.out.println("Name of the dog"+ idDetails);
		setSysProperty("dogID",idDetails.toString());
		 
		
		//System.out.println(resp.asPrettyString());
	}
	 
	@Test(priority=2)
	public void GetPetID() throws Exception
	{
		
		System.out.println("Getting property Details " + getSysProperty("dogID"));
		
		String requestURL="https://petstore.swagger.io/v2/pet/"+getSysProperty("dogID");
		 
		Response resp = getServiceResponse(requestURL);
		
		System.out.println(resp.getStatusCode());
		
		System.out.println(resp.body());
		
		Integer idDetails = com.jayway.jsonpath.JsonPath.read(resp.asString(), "$.id");
			
	    System.out.println("Name of the dog"+ idDetails);
	 
		
	
	}















	@Test(enabled=false)
	public void validateFindByStatus() throws Exception
	{
		String requestURL="https://petstore.swagger.io/v2/pet/findByStatus?status=available";

		Response resp = getServiceResponse(requestURL);
		
		System.out.println(resp.getStatusCode());
		
		System.out.println(resp.body());
		
		//System.out.println(resp.asPrettyString());
		
		List<String>  dogName = com.jayway.jsonpath.JsonPath.read(resp.asString(), "$..[0].category.name");
		
		System.out.println("Name of the dog"+ dogName.get(0));
	}

}
