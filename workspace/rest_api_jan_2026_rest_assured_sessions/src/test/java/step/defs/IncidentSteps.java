package step.defs;

import java.util.List;
import java.util.Map;

import org.hamcrest.Matchers;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import week3.day2.CreateIncidentPojo;

public class IncidentSteps {
	
	private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	private Response response;
	private CreateIncidentPojo createIncidentPojo = new CreateIncidentPojo();

	@Given("user should set baseuri as {string} of the servicenow api")
	public void user_should_set_baseuri_as_of_the_servicenow_api(String baseUri) {
		requestSpecBuilder.setBaseUri(baseUri);
	}

	@Given("user should set basepath as {string} of the servicenow api")
	public void user_should_set_basepath_as_of_the_servicenow_api(String basePath) {
		requestSpecBuilder.setBasePath(basePath);
	}

	@Given("user should set basic authenication username as {string} and password as {string}")
	public void user_should_set_basic_authenication_username_as_and_password_as(String username, String password) {
		requestSpecBuilder.setAuth(RestAssured.basic(username, password));
	}

	@When("user should hit get method to retrieve all records from the incident table")
	public void user_should_hit_get_method_to_retrieve_all_records_from_the_incident_table() {
		response = RestAssured.given().spec(requestSpecBuilder.build()).get("/incident");
	}

	@Then("user should see the status code should be {string}")
	public void user_should_see_the_status_code_should_be(String statusCode) {
		response.then().statusCode(Integer.parseInt(statusCode));
	}

	@Then("user should see the status line should be {string}")
	public void user_should_see_the_status_line_should_be(String statusLine) {
		response.then().statusLine(Matchers.containsString(statusLine));
	}

	@Then("user should get response in the {string} format")
	public void user_should_get_response_in_the_format(String responseFormat) {
		if (responseFormat.equalsIgnoreCase("JSON")) {
			response.then().contentType(ContentType.JSON);
		} else if (responseFormat.equalsIgnoreCase("XML")) {
			response.then().contentType(ContentType.XML);
		}
	}
	
	@Given("user should set header key as {string} and value as {string}")
	public void user_should_set_header_key_as_and_value_as(String key, String value) {
	    requestSpecBuilder.addHeader(key, value);
	}
	
	@Given("user should enter the short description as {string} in the request body")
	public void user_should_enter_the_short_description_as_in_the_request_body(String shortDescription) {
	   createIncidentPojo.setShort_description(shortDescription);
	}

	@When("user should hit post method to create new record in the incident table")
	public void user_should_hit_post_method_to_create_new_record_in_the_incident_table() {
		response = RestAssured.given().spec(requestSpecBuilder.build()).log().all().when().body(createIncidentPojo)
				.post("/incident");
	}
	
	@Then("user should get the success response with following expected values")
	public void user_should_get_the_success_response_with_following_expected_values(DataTable dataTable) {
	    List<Map<String, String>> asMaps = dataTable.asMaps();
	    for (Map<String, String> map : asMaps) {
	    	response.then().statusCode(Integer.parseInt(map.get("statusCode")));
	    	response.then().statusLine(Matchers.containsString(map.get("statusLine")));
	    	if (map.get("responseFormat").equalsIgnoreCase("JSON")) {
				response.then().contentType(ContentType.JSON);
			} else if (map.get("responseFormat").equalsIgnoreCase("XML")) {
				response.then().contentType(ContentType.XML);
			}
		}
	}
	
	@Then("user should see the {string} value in the short_description key in response")
	public void user_should_see_the_value_in_the_short_description_key_in_response(String expectedValue) {
	    response.then().assertThat().body("result.short_description", Matchers.equalTo(expectedValue));
	}
	
	@Given("user should enter the required values in the request body")
	public void user_should_enter_the_required_values_in_the_request_body(DataTable dataTable) {
		List<Map<String, String>> asMaps = dataTable.asMaps();	    
		for (Map<String, String> map : asMaps) {
			createIncidentPojo.setShort_description(map.get("shortDescription"));
			createIncidentPojo.setDescription(map.get("description"));
		}
	}
}