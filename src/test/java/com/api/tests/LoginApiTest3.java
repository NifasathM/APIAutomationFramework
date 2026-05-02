package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


@Listeners(com.api.listeners.TestListener.class)
public class LoginApiTest3 {
	
	@Test(description ="Verify if Login API is working")
	public void LoginTest() {
		
		LoginRequest loginRequest = new LoginRequest("Nifasath", "Nifasath@123");
		AuthService authService = new AuthService();
		Response response = authService.login(loginRequest);
		//Deserialization (Convert the API JSON response into a Java object)
		LoginResponse loginResponse=  response.as(LoginResponse.class);
		
		System.out.println(response.asPrettyString());
		System.out.println(loginResponse.getToken());
		System.out.println(loginResponse.getType());
		
		Assert.assertTrue(loginResponse.getToken()!= null);
		Assert.assertEquals(loginResponse.getType(), "Bearer");
		
		
		
		
		
	}

}
/* without Deserialization we access the value from response like below 
 * response.jsonPath().getString("token")
 */

