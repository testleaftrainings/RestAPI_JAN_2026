package com.testleaf.matschie.servicenow.services;

import java.io.File;

import org.hamcrest.Matchers;

import com.testleaf.matschie.rest.assured.api.client.RestAssuredApiClient;
import com.testleaf.matschie.servicenow.deserialization.pojos.Result;
import com.testleaf.matschie.servicenow.serialization.pojos.UpdateRecordPayload;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class IncidentService {	
	
	// Create the object for the rest assured base class(RestAssuredApiClient)
	private RestAssuredApiClient apiClient = new RestAssuredApiClient();
	// Create refrence for the response interface, which is used to store current response value
	private Response response;
	private static final String TABLE_NAME = "incident";
	
	// Create new method for incident table record creation without request body
	public IncidentService createNewRecord(RequestSpecBuilder requestSpecBuilder) {
		response = apiClient.post(requestSpecBuilder.addHeader("Content-Type", "application/json"), 
				TABLE_NAME, null);
		return this;
	}
	
	// Fetch the existing record inside the incident table based sys_id value
	public IncidentService getRecordBySysId(RequestSpecBuilder requestSpecBuilder, String sysId) {
		response = apiClient.get(requestSpecBuilder, TABLE_NAME+File.separator+sysId);
		return this;
	}
	
	public IncidentService updateExistingRecord(RequestSpecBuilder requestSpecBuilder, String sysId, UpdateRecordPayload Payload) {
		response = apiClient.put(requestSpecBuilder, TABLE_NAME+File.separator+sysId, Payload);
		return this;
	}
	
	// After getting response we're validating the current JSON response
	public IncidentService validateJsonResponse(int statusCode, String statusLine) {
		response.then().assertThat()
		        .statusCode(statusCode)
		        .statusLine(Matchers.containsString(statusLine))
		        .contentType(ContentType.JSON);
		return this;
	}
	
	// This method is used to validate response time based on the given SLA
	public IncidentService validateResponseTimeSLA(long expectedValue) {
		response.then().assertThat()
		        .time(Matchers.lessThanOrEqualTo(expectedValue));
		return this;
	}
	
	// This method is used to validate the create new record response default values
	public IncidentService validateCreateResponseDefaultValues() {
		response.then().assertThat()
		 .body("result", Matchers.hasKey("sys_id"))
		 .body("result.sys_id", Matchers.not(Matchers.emptyOrNullString()))
		 .body("result.short_description", Matchers.emptyString())
		 .body("result.description", Matchers.emptyString())
		 .body("result.category", Matchers.equalTo("inquiry"));
		return this;
	}
	
	// We're extracting sys_id value from the current response
	public String extractSysId() {
		Result result = response.then().extract().jsonPath().getObject("result", Result.class);
		return result.getSysId();
	}
	
	public IncidentService validateSysIdValue(String expected) {
		response.then().assertThat().body("result.sys_id", Matchers.equalTo(expected));
		return this;
	}
	
	public IncidentService validateShortDescriptionValue(String expected) {
		response.then().assertThat().body("result.short_description", Matchers.equalTo(expected));
		return this;
	}
	
	public IncidentService validateDescriptionValue(String expected) {
		response.then().assertThat().body("result.description", Matchers.equalTo(expected));
		return this;
	}
	
	public IncidentService validateCategoryValue(String expected) {
		response.then().assertThat().body("result.category", Matchers.equalTo(expected));
		return this;
	}	

}