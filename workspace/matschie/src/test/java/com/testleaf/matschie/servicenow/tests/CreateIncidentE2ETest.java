package com.testleaf.matschie.servicenow.tests;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.testleaf.matschie.general.utils.AllureHandler;
import com.testleaf.matschie.servicenow.serialization.pojos.UpdateRecordPayload;
import com.testleaf.matschie.servicenow.services.IncidentService;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;

public class CreateIncidentE2ETest {
	
	RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	IncidentService incidentService = new IncidentService();
	UpdateRecordPayload updateRecordPayload = new UpdateRecordPayload();
	String sysId;
	
	@BeforeClass
	public void beforeClass() {
		requestSpecBuilder.setBaseUri("https://dev324941.service-now.com");
		requestSpecBuilder.setBasePath("/api/now/table/");
		requestSpecBuilder.setAuth(RestAssured.basic("admin", "e5!pRsPN%lH5"));
	}
	
	@Test
	public void createNewIncidentRecord() {
		sysId = incidentService.createNewRecord(requestSpecBuilder)
		               .validateJsonResponse(201, "Created")
		               .validateResponseTimeSLA(5000L)
		               .validateCreateResponseDefaultValues()
		               .extractSysId();
	}
	
	@Test
	public void getExistingIncidentRecordDetials() {
		incidentService.getRecordBySysId(requestSpecBuilder, sysId)
		               .validateJsonResponse(200, "OK")
		               .validateResponseTimeSLA(3000L)
		               .validateSysIdValue(sysId);
	}
	
	@Test
	public void updatedExistingRecord() {
		updateRecordPayload.setShortDescription("RestAPISessionJan2025");
		updateRecordPayload.setDescription("Update the description key value based on PUT method");
		updateRecordPayload.setCategory("hardware");
		incidentService.updateExistingRecord(requestSpecBuilder, sysId, updateRecordPayload)
		               .validateJsonResponse(200, "OK")
		               .validateSysIdValue(sysId)
		               .validateShortDescriptionValue(updateRecordPayload.getShortDescription())
		               .validateDescriptionValue(updateRecordPayload.getDescription())
		               .validateCategoryValue(updateRecordPayload.getCategory());
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		AllureHandler.moveHistoryFolderToAllureResults();
	}

}