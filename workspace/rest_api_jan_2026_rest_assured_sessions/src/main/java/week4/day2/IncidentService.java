package week4.day2;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class IncidentService {
	
	private Response response;
	
	public void createIncident(RequestSpecBuilder requestSpecBuilder) {
		response = given()
		 .spec(requestSpecBuilder.build())
		 .header("Content-Type", "application/json")
		 .log().all()
		 .when()		 
		 .post("/{table_name}");
	}
	
	public void getIncidentBySysId(RequestSpecBuilder requestSpecBuilder, String sysId) {
		response = given()
		 .spec(requestSpecBuilder.build())
		 .pathParam("sys_id", sysId)
		.when()
		 .get("/{table_name}/{sys_id}");
	}
	
	public void updateIncident(RequestSpecBuilder requestSpecBuilder, Object pojoClass, String sysId) {
		response = given()
		 .spec(requestSpecBuilder.build())
		 .pathParam("sys_id", sysId)
		 .header("Content-Type", "application/json")
		 .log().all()
		.when()
		 .body(pojoClass)
		 .put("/{table_name}/{sys_id}");
	}
	
	public void deleteIncident(RequestSpecBuilder requestSpecBuilder, String sysId) {
		response = given()
		 .spec(requestSpecBuilder.build())
		 .pathParam("sys_id", sysId)
		.when()
		 .delete("/{table_name}/{sys_id}");
	}
	
	public String extractSysId() {	
		return response.then().extract().jsonPath().getString("result.sys_id");
	}
	
	public void validataSuccessResponse(int statusCode, String statusLine) {
		response.then()
		        .log().ifValidationFails(LogDetail.ALL)
		        .assertThat()
		        .statusCode(statusCode)
		        .statusLine(Matchers.containsString(statusLine));
	}
	
	public void validataSuccessJsonResponse(int statusCode, String statusLine) {
		response.then()
		        .log().ifValidationFails(LogDetail.ALL)
		        .assertThat()
		        .statusCode(statusCode)
		        .statusLine(Matchers.containsString(statusLine))
		        .contentType(ContentType.JSON);
	}
	
	public void validateNotFoundResponse() {
		response.then()
                .log().ifValidationFails(LogDetail.ALL)
                .assertThat()
                .statusCode(404)
                .statusLine(Matchers.containsString("Not Found"))
                .contentType(ContentType.JSON);
	}

}