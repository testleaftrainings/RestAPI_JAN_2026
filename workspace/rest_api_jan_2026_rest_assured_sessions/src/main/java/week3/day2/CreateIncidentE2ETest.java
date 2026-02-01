package week3.day2;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class CreateIncidentE2ETest {
	
	private String sysId;
	private UpdateIncidentPojo updateIncidentPojo = new UpdateIncidentPojo();
	
	@Test(priority = 1)
	public void createIncidentRecord() {
		sysId = given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")
		 .header("Content-Type", "application/json")
		 .log().all()
		.when()		 
		 .post("/{table_name}")
		.then()
		 .log().ifValidationFails(LogDetail.ALL)
		 .assertThat()
		 .statusCode(201)
		 .statusLine(Matchers.containsString("Created"))
		 .contentType(ContentType.JSON)
		 .time(Matchers.lessThan(5000L))
		 .body("result", Matchers.hasKey("sys_id"))
		 .body("result.sys_id", Matchers.not(Matchers.emptyOrNullString()))
		 .body("result.short_description", Matchers.emptyString())
		 .body("result.description", Matchers.emptyString())
		 .body("result.category", Matchers.equalTo("inquiry"))
		 .extract()
		 .jsonPath()
		 .getString("result.sys_id");
		System.out.println(sysId);
	}
	
	@Test(priority = 2)
	public void getExistingIncidentRecord() {
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")
		 .pathParam("sys_id", sysId)
		.when()
		 .get("/{table_name}/{sys_id}")
		.then()
		 .log().ifValidationFails(LogDetail.ALL)
		 .assertThat()
		 .statusCode(200)
		 .statusLine(Matchers.containsString("OK"))
		 .contentType(ContentType.JSON)
		 .time(Matchers.lessThan(5000L))
		 .body("result", Matchers.hasKey("sys_id"))
		 .body("result.sys_id", Matchers.not(Matchers.emptyOrNullString()))
		 .body("result.sys_id", Matchers.equalTo(sysId))
		 .body("result.short_description", Matchers.emptyString())
		 .body("result.description", Matchers.emptyString())
		 .body("result.category", Matchers.equalTo("inquiry"));
	}
	
	@Test(priority = 3)
	public void updateExistingIncidentRecord() {
		
		updateIncidentPojo.setShort_description("APISESSIONJAN2025");
		updateIncidentPojo.setDescription("Update the default value of the description usin put method");
		updateIncidentPojo.setCategory("Hardware");
		
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")
		 .pathParam("sys_id", sysId)
		 .header("Content-Type", "application/json")
		 .log().all()
		.when()
		 .body(updateIncidentPojo)
		 .put("/{table_name}/{sys_id}")
		.then()
		 .log().ifValidationFails(LogDetail.ALL)
		 .assertThat()
		 .statusCode(200)
		 .statusLine(Matchers.containsString("OK"))
		 .contentType(ContentType.JSON)
		 .time(Matchers.lessThan(5000L))
		 .body("result", Matchers.hasKey("sys_id"))
		 .body("result.sys_id", Matchers.not(Matchers.emptyOrNullString()))
		 .body("result.sys_id", Matchers.equalTo(sysId))
		 .body("result.short_description", Matchers.equalTo(updateIncidentPojo.getShort_description()))
		 .body("result.description", Matchers.equalTo(updateIncidentPojo.getDescription()))
		 .body("result.category", Matchers.equalToIgnoringCase(updateIncidentPojo.getCategory()));
	}
	
	@Test(priority = 4)
	public void deleteExistingIncidentRecord() {
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")
		 .pathParam("sys_id", sysId)
		.when()
		 .delete("/{table_name}/{sys_id}")
		.then()
		 .log().ifValidationFails(LogDetail.ALL)
		 .assertThat()
		 .statusCode(204)
		 .statusLine(Matchers.containsString("No Content"))
		 .time(Matchers.lessThan(5000L));
	}
	
	@Test(priority = 5)
	public void isRecordDeletedSuccessfully() {
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")
		 .pathParam("sys_id", sysId)
		.when()
		 .get("/{table_name}/{sys_id}")
		.then()
		 .log().ifValidationFails(LogDetail.ALL)
		 .assertThat()
		 .statusCode(404)
		 .statusLine(Matchers.containsString("Not Found"))
		 .contentType(ContentType.JSON)
		 .time(Matchers.lessThan(5000L));
	}

}