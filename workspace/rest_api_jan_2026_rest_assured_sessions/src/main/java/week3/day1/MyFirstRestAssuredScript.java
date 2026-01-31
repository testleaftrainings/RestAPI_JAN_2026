package week3.day1;

import io.restassured.RestAssured;

public class MyFirstRestAssuredScript {

	public static void main(String[] args) {
		RestAssured.given()
		           .auth()
		           .basic("admin", "e5!pRsPN%lH5")
		           .when()				           
		           .get("https://dev324941.service-now.com/api/now/table/incident")
		           .then()
		           .assertThat()
		           .statusCode(200);
	}

}