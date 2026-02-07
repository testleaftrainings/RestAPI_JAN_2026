package step.defs;

import org.hamcrest.Matchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class IncidentSteps {
	
	private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	private Response response;

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

}