package org.iitwforce.restassured.restassured;

import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class PetStoreTests extends RestLibrary {

	Response response;

	@Test(description="Place an order for the Pet")
	public void validatePetOrderCreation() throws Exception
	{

		String requestURL = pro.getProperty("createpetorder");
		//		String payloadString="{\r\n"
		//				+ "  \"id\": 1,\r\n"
		//				+ "  \"petId\": 1,\r\n"
		//				+ "  \"quantity\": 1,\r\n"
		//				+ "  \"shipDate\": \"2024-05-10T01:46:16.159Z\",\r\n"
		//				+ "  \"status\": \"placed\",\r\n"
		//				+ "  \"complete\": true\r\n"
		//				+ "}";

		String payloadString=	readPayload("OrderNewPet_payload.txt");
		response = postServiceResponse(requestURL,payloadString);
		SoftAssert sa = new SoftAssert();
		int expected = 200;
		int actual = response.getStatusCode();
		//		String responseBody = response.asPrettyString();
		System.out.println(response.asPrettyString());
		System.out.println(response.asString());
		String actualStatus = com.jayway.jsonpath.JsonPath.read(response.asString(), "$.status");
		String expectedStatus="placed";
		sa.assertEquals(actualStatus,expectedStatus);
		sa.assertEquals(actual,expected);
		sa.assertAll();
	}
	@Test(description="Add a New Pet",enabled=false) 
	public void verifyAddNewPet() {
		try 
		{
			String payloadString = readPayload("AddPetPayload.txt");
			response =  postServiceResponse(pro.getProperty("addpetstore"),payloadString);	 
			System.out.println(response.getStatusCode());//429
			//System.out.println(response.prettyPrint());
			ResponseBody respBody = response.getBody();
			String actual = respBody.asString();
			System.out.println("Response Body" + actual);
			boolean result = actual.contains("doggie");
			Assert.assertTrue(result);
			String dogName = com.jayway.jsonpath.JsonPath.read(response.asString(), "$.name");
			System.out.println(dogName);


		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail("Unable to Retrieve information for employees");
		}
	}


	@Parameters({"petsoldstatus"})
	@Test(description="Finds Pets By Sold Status")

	public void validatefindBySoldStatus(String petsoldstatus) throws Exception
	{
		String requestURL = pro.getProperty("findbypetstatus");
		requestURL= requestURL+petsoldstatus;
		response = getServiceResponse(requestURL);
		SoftAssert sa = new SoftAssert();
		int expected = 200;
		int actual = response.getStatusCode();
		String responseBody = response.asPrettyString();
		System.out.println("response body::" + responseBody);
		System.out.println(response.asString());
		String actualStatus = com.jayway.jsonpath.JsonPath.read(response.asString(), "$.[0].status");
		String expectedStatus=petsoldstatus;
		sa.assertEquals(actualStatus,expectedStatus);
		sa.assertEquals(actual,expected);
		sa.assertAll();

	}
	@Parameters({"petavailablestatus"})
	@Test(description="Finds Pets By Available Status")

	public void validatefindByAvailableStatus(String petavailablestatus) throws Exception
	{
		String requestURL = pro.getProperty("findbypetstatus");
		requestURL= requestURL+petavailablestatus;
		response = getServiceResponse(requestURL);
		SoftAssert sa = new SoftAssert();
		int expected = 200;
		int actual = response.getStatusCode();
		//		String responseBody = response.asPrettyString();
		System.out.println(response.asString());
		String actualStatus = com.jayway.jsonpath.JsonPath.read(response.asString(), "$.[0].status");
		String expectedStatus=petavailablestatus;
		sa.assertEquals(actualStatus,expectedStatus);
		sa.assertEquals(actual,expected);
		sa.assertAll();

	}
	@Parameters({"petpendingstatus"})
	@Test(description="Finds Pets By Available Status")

	public void validatefindByPendingStatus(String petpendingstatus) throws Exception
	{
		String requestURL = pro.getProperty("findbypetstatus");
		requestURL= requestURL+petpendingstatus;
		response = getServiceResponse(requestURL);
		SoftAssert sa = new SoftAssert();
		int expected = 200;
		int actual = response.getStatusCode();
		String responseBody = response.asPrettyString();
		System.out.println("Response Body"+ responseBody);
		System.out.println(response.asString());
		String actualStatus = com.jayway.jsonpath.JsonPath.read(response.asString(), "$.[0].status");
		String expectedStatus=petpendingstatus;
		sa.assertEquals(actualStatus,expectedStatus);
		sa.assertEquals(actual,expected);
		sa.assertAll();

	}













}
