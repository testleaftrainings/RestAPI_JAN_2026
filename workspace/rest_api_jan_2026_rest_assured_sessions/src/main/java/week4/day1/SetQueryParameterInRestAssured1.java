package week4.day1;

import java.util.List;

import org.hamcrest.Matchers;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class SetQueryParameterInRestAssured1 {

	public static void main(String[] args) {
		List<Result> records = RestAssured.given()
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
        .extract()
        .jsonPath()
        .getList("result", Result.class);
		
		System.out.println(records.size());
		
		for (Result record : records) {			
			Assert.assertTrue(record.getCategory().equalsIgnoreCase("hardware"));
			Assert.assertTrue(record.getState().equals("2"));
		}		
		
	}	

}