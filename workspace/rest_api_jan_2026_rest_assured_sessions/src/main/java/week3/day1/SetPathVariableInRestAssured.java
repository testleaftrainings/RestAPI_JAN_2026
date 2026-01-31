package week3.day1;

import io.restassured.RestAssured;

public class SetPathVariableInRestAssured {

	public static void main(String[] args) {
		RestAssured.given()
        .auth()
        .basic("admin", "e5!pRsPN%lH5")
        .pathParam("table_name", "incident")
        .log().all() // Print the request information
        .when()				           
        .get("https://dev324941.service-now.com/api/now/table/{table_name}")
        .then()
        .log().all() // Print the response information
        .assertThat()
        .statusCode(200);		
	}

}
