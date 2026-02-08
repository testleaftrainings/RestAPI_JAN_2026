package week4.day2;

import org.testng.annotations.Test;

public class CreateIncidentE2ETest extends ServiceNowBase {	
	
	IncidentService incidentService = new IncidentService();
	
	@Test(priority = 1)
	public void createIncidentRecord() {
		incidentService.createIncident(requestSpecBuilder);
		incidentService.validataSuccessJsonResponse(201, "Created");
		sysId = incidentService.extractSysId();
	}
	
	@Test(priority = 2)
	public void getExistingIncidentRecord() {
		incidentService.getIncidentBySysId(requestSpecBuilder, sysId);
		incidentService.validataSuccessJsonResponse(200, "OK");
	}
	
	@Test(priority = 3)
	public void updateExistingIncidentRecord() {
		
		updateIncidentPojo.setShort_description("APISESSIONJAN2025");
		updateIncidentPojo.setDescription("Update the default value of the description usin put method");
		updateIncidentPojo.setCategory("Hardware");
		
		incidentService.updateIncident(requestSpecBuilder, updateIncidentPojo, sysId);
		incidentService.validataSuccessJsonResponse(200, "OK");
		
	}
	
	@Test(priority = 4)
	public void deleteExistingIncidentRecord() {
		incidentService.deleteIncident(requestSpecBuilder, sysId);
		incidentService.validataSuccessResponse(204, "No Content");
	}
	
	@Test(priority = 5)
	public void isRecordDeletedSuccessfully() {
		incidentService.getIncidentBySysId(requestSpecBuilder, sysId);
		incidentService.validateNotFoundResponse();
	}

}