package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.APIUtilities;
import resources.ApiResources;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

public class ValidatePlaceStepDefination extends APIUtilities{
	RequestSpecification request;
	static Response response;
	static String placeId;

	@Given("addPlace api payload using {string} and {string}")
	public void add_place_api_payload_using_and(String name, String language) throws IOException {
		this.request = given().spec(getRequestBuilder()).body(getAddPlaceJson(name, language)).queryParam("key", "qaclick123");   
	}
	@When("user hit the {string} api using valid data with {string} http request")
	public void user_hit_the_api_using_valid_data_with_http_request(String apiResource, String httpRequest) {
		ApiResources apiResources =  ApiResources.valueOf(apiResource);
		if (httpRequest.equals("POST"))
			response = this.request.when().post(apiResources.getApiResource());
		else if (httpRequest.equals("GET"))
			response = this.request.when().get(apiResources.getApiResource());
		else if (httpRequest.equals("PUT"))
			response = this.request.when().put(apiResources.getApiResource());
	}
	@Then("the api call status is {int}")
	public void the_api_call_status_is(int statusCode) {
	    response.then().assertThat().statusCode(statusCode);
	}
	@Then("the {string} response should be {string}")
	public void the_response_should_be(String key, String value) {
		String reponse = response.then().extract().response().asString();
		assertEquals(value, getJsonData(reponse, key));
	}
	@Given("getPlace api")
	public void get_place_api() throws IOException {
		String reponse = response.then().extract().response().asString();
		placeId = getJsonData(reponse, "place_id");
	    this.request = given().spec(getRequestBuilder()).queryParam("key", "qaclick123")
	    		.queryParam("place_id", placeId);
	}
	@Given("deletePlace api payload")
	public void delete_place_api_payload() throws IOException {
	    this.request = given().spec(getRequestBuilder()).body(deletePlacePayload(placeId)).queryParam("key", "qaclick123");
	}

}
