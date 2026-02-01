package week3.day2;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;

import io.restassured.http.ContentType;
import week3.day2.pojos.CreateIncident;

public class CreateNewIncidentRecordPassRequestBodyAsPojoObject {
	
	static CreateIncident createIncidentPojo = new CreateIncident();
	
	public static void main(String[] args) {
	
		//Serialization is convert JAVA object to JSON Object
		
		createIncidentPojo.setShortDescription("POJOCLASS");
		createIncidentPojo.setDescription("Create record based on Pojo");
		createIncidentPojo.setCategory("software");
		
		String sysId = given()
		 .baseUri("https://dev324941.service-now.com")
		 .basePath("api/now/table")
		 .auth()
		 .basic("admin", "e5!pRsPN%lH5")
		 .pathParam("table_name", "incident")
		 .header("Content-Type", "application/json")
		 .log().all()
		.when()
		 .body(createIncidentPojo)
		 .post("/{table_name}")
		.then()
		 .log().all()
		 .assertThat()
		 .statusCode(201)
		 .statusLine(Matchers.containsString("Created"))
		 .contentType(ContentType.JSON)
		 .time(Matchers.lessThan(5000L))
		 .body("result", Matchers.hasKey("sys_id"))
		 .body("result.sys_id", Matchers.not(Matchers.emptyOrNullString()))
		 .body("result.short_description", Matchers.equalTo(createIncidentPojo.getShortDescription()))
		 .body("result.description", Matchers.equalTo(createIncidentPojo.getDescription()))
		 .body("result.category", Matchers.equalTo(createIncidentPojo.getCategory()))
		 .extract()
		 .jsonPath()
		 .getString("result.sys_id");
		
		System.out.println(sysId);
	}

}
