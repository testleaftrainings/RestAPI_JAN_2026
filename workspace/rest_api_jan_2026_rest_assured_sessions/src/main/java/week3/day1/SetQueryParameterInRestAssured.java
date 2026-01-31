package week3.day1;

import io.restassured.RestAssured;

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
        .statusCode(200);
	}	

}
