package com.api.base;

import static io.restassured.RestAssured.*;
import com.api.filters.LoggingFilter;
import com.api.models.request.LoginRequest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseService {// wrapper for Rest Assured 
	
	//base uri 
	//creating request 
	//handling response 
	
	
	//here the url is constant so we use final so when we mention final then it comes with static  so it has to be added
	private static final String BASE_URL="http://64.227.160.186:8080";
	private RequestSpecification requestSpecification;
	
	
	static
	{
		RestAssured.filters(new LoggingFilter());
	}
	
	public BaseService() {

		requestSpecification = given().baseUri(BASE_URL);	
	}
	
	protected void setAuthToken(String token) {	
		requestSpecification.header("Authorization", "Bearer "+ token);	
	}
	
	//we will make this method loosely coupled  
	//We use Object as payload not only for automatic serialization to JSON, 
	//but also to make the method loosely coupled and reusable.
	//This allows the same method to handle different types of request payloads instead of being restricted to a specific class.
	protected Response postRequest(Object payload, String endpoint) {
		return requestSpecification.contentType(ContentType.JSON).body(payload).post(endpoint);
	}
	
	protected Response putRequest(Object payload, String endpoint) {
		return requestSpecification.contentType(ContentType.JSON).body(payload).put(endpoint);
	}
	
	protected Response getRequest(String endpoint) {
		return requestSpecification.get(endpoint);
	}
	
	
}

/*if the base url is also changes then we write as below 
requestSpecification = given();

protected Response postRequest(String baseUrl, Object payload, String endpoint ) {
return requestSpecification.baseUri(baseUrl).contentType(ContentType.JSON).body(payload).post(endpoint);
}*/


//on postRequest payload
/*We use Object so the same method can accept different request models like LoginRequest,SignUpRequest, or HashMap.
 * This avoids duplicate methods and makes the framework reusable and scalable.
 */