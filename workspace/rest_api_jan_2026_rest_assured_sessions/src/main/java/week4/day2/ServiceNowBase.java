package week4.day2;

import static io.restassured.RestAssured.basic;

import org.testng.annotations.BeforeClass;

import io.restassured.builder.RequestSpecBuilder;
import week3.day2.UpdateIncidentPojo;

public class ServiceNowBase {
	
	protected String sysId;
	protected UpdateIncidentPojo updateIncidentPojo = new UpdateIncidentPojo();
	protected RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	
	@BeforeClass
	public void beforeClass() {
		requestSpecBuilder.setBaseUri("https://dev324941.service-now.com");
		requestSpecBuilder.setBasePath("api/now/table");
		requestSpecBuilder.setAuth(basic("admin", "e5!pRsPN%lH5"));
		requestSpecBuilder.addPathParam("table_name", "incident");
	}

}