package week3.day1;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import io.restassured.filter.log.LogDetail;

public class DeleteExistingIncidentRecord {

	public static void main(String[] args) {
		given()
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("tableName", "incident")
		 .pathParam("sysId", "ef43c6d40a0a0b5700c77f9bf387afe3")
		 .log().all()
		.when()
		  .delete("https://dev324941.service-now.com/api/now/table/{tableName}/{sysId}")
		.then()
		  .log().ifValidationFails(LogDetail.ALL)
		  .assertThat()
		  .statusCode(204)
		  .statusLine(Matchers.containsString("No Content"))
		  .time(Matchers.lessThan(5000L));
	}

}