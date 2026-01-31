package week3.day1;

import static io.restassured.RestAssured.*;

import org.hamcrest.Matchers;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;

public class RetrieveASingleIncidentRecord {

	public static void main(String[] args) {
		given()
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("tableName", "incident")
		 .pathParam("sysId", "ef43c6d40a0a0b5700c77f9bf387afe3")
		 .log().all()
		.when()
		  .get("https://dev324941.service-now.com/api/now/table/{tableName}/{sysId}")
		.then()
		  .log().ifValidationFails(LogDetail.ALL)
		  .assertThat()
		  .statusCode(200)
		  .statusLine(Matchers.containsString("OK"))
		  .contentType(ContentType.JSON);
	}

}