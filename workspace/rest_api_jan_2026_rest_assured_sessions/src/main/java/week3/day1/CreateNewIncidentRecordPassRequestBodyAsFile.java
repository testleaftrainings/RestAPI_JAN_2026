package week3.day1;

import static io.restassured.RestAssured.given;

import java.io.File;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;

public class CreateNewIncidentRecordPassRequestBodyAsFile {
	
	static File requestBody = new File("src/main/resources/request_body/create_incident.json");
	
	public static void main(String[] args) {
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")
		 .header("Content-Type", "application/json")
		 .log().all()
		.when()
		 .body(requestBody)
		 .post("/{table_name}")
		.then()
		 .log().all()
		 .assertThat()
		 .statusCode(201)
		 .statusLine(Matchers.containsString("Created"))
		 .contentType(ContentType.JSON);
	}

}
