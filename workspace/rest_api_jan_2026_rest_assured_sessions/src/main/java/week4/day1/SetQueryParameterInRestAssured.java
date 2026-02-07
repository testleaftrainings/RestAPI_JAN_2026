package week4.day1;

import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class SetQueryParameterInRestAssured {

	public static void main(String[] args) {
		RestAssured.given()
        .auth()
        .basic("admin", "e5!pRsPN%lH5")
        .pathParam("table_name", "incident")
        .queryParam("category", "hardware")
        .queryParam("sysparm_fields", "sys_id,active,state,category")
        .queryParam("state", "2")
        .log().all()
        .when()				           
        .get("https://dev324941.service-now.com/api/now/table/{table_name}")
        .then()
        .log().all() 
        .assertThat()
        .statusCode(200)
        .statusLine(Matchers.containsString("OK"))// HTTP1.1 200 OK
        .contentType(ContentType.JSON)
        .body("result", Matchers.hasSize(3))
        .body("result", Matchers.everyItem(Matchers.hasKey("sys_id")))
        .body("result", Matchers.everyItem(Matchers.hasKey("active")))
        .body("result", Matchers.everyItem(Matchers.hasKey("state")))
        .body("result", Matchers.everyItem(Matchers.hasKey("category")))
        .body("result.state", Matchers.everyItem(Matchers.equalTo("2")))
        .body("result.category", Matchers.everyItem(Matchers.equalToIgnoringCase("hardware")));
	}	

}
