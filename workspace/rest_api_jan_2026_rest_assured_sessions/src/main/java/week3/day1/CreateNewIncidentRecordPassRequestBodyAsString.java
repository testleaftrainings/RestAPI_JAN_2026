package week3.day1;

import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;
import io.restassured.http.ContentType;

public class CreateNewIncidentRecordPassRequestBodyAsString {
	
	static String requestBody = """
			                    {
                                  "short_description": "APISESSIONFEB26",
                                  "description": "Create new record based on POST method"
                                }
			                    """;

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
