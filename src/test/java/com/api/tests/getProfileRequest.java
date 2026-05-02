package com.api.tests;

import org.testng.annotations.Test;

import com.api.base.AuthService;
import com.api.base.UserProfileManagementService;
import com.api.models.request.LoginRequest;
import com.api.models.response.LoginResponse;
import com.api.models.response.UserProfileResponse;

import io.restassured.response.Response;

public class getProfileRequest {
	//step 1  login 
	//step 2  (if credentials are correct then )Go to profile section to get profile information
	
	@Test(description="Verify the Profile Information")
	
	public void getProfileInfoTest() {
		
		//doing authentication (login) 
		
		AuthService authService = new AuthService();
		Response response = authService.login(new LoginRequest("Nifasath","Nifasath@123"));
		LoginResponse loginResponse = response.as(LoginResponse.class);
		System.out.println(loginResponse.getToken());
		
		//if credentials are correct so we get profile details
		UserProfileManagementService userProfileManagementService = new UserProfileManagementService();
		response = userProfileManagementService.getProfile(loginResponse.getToken());
		UserProfileResponse userProfileResponse = response.as(UserProfileResponse.class);
		System.out.println(userProfileResponse.getUsername());
		
	}

}
