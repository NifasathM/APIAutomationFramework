package com.api.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.models.request.SignUpRequest;

import io.restassured.response.Response;

public class AccountCreationTest {
	
	@Test(description ="Verify if Login API is working")
	public void createAccountTest() {
		SignUpRequest signUpRequest = new SignUpRequest.Builder()
				.username("Nifasathh")
				.email("nifa1233@gmail.com")
				.password("nifapassword")
				.firstName("Nifasath")
				.lastName("M")
				.mobileNumber("7808600321").build();
		
		AuthService authService = new AuthService();
		Response response = authService.signup(signUpRequest);
		Assert.assertEquals(response.asPrettyString(), "User registered successfully!");
		//System.out.println(response.asPrettyString());
	}

}
