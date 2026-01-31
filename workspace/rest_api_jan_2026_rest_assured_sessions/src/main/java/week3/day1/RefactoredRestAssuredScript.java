package week3.day1;

import static io.restassured.RestAssured.*;

import java.util.HashMap;
import java.util.Map;

public class RefactoredRestAssuredScript {

	public static void main(String[] args) {		
		
		Map<String, String> qureyParamsMap = new HashMap<String, String>();		
		qureyParamsMap.put("category", "hardware");
		qureyParamsMap.put("sysparm_fields", "sys_id,active,state,category");
		qureyParamsMap.put("state", "2");
		
		given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")
		 .queryParams(qureyParamsMap)
		 .log().all()
		.when()
		 .get("/{table_name}")
		.then()
		 .assertThat()
		 .statusCode(200);
	
	}

}
