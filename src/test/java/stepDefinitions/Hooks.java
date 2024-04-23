package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace )
	public void addPlace() throws IOException
	{
		ValidatePlaceStepDefination sd = new ValidatePlaceStepDefination();
		sd.add_place_api_payload_using_and("Kiran QA", "Selenium");
		sd.user_hit_the_api_using_valid_data_with_http_request("AddPlace", "POST");
		sd.get_place_api();
	}

}
