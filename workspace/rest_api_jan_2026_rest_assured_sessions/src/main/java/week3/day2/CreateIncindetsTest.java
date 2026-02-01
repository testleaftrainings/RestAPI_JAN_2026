package week3.day2;

import static io.restassured.RestAssured.given;

import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class CreateIncindetsTest {
	
	CreateIncidentPojo createIncidentPojo = new CreateIncidentPojo();
	
	@DataProvider
	public String[][] testDatas() {
		return new String[][] {
			{"POJOCLASS1", "Create record based on Pojo1"},
			{"POJOCLASS2", "Create record based on Pojo2"},
			{"POJOCLASS3", "Create record based on Pojo3"},
			{"POJOCLASS3", "Create record based on Pojo4"}
		};
	}
	
	@Test(dataProvider = "testDatas")
	public void createIncidentRecords(String shortDescription, String description) {
		
		createIncidentPojo.setShort_description(shortDescription);
		createIncidentPojo.setDescription(description);
		
		given()
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
		 .contentType(ContentType.JSON);
	}

}