package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class APIUtilities {
	static RequestSpecification requestBuilder;

	public RequestSpecification getRequestBuilder() throws IOException {
		if (requestBuilder == null) {
			PrintStream stream = new PrintStream(new FileOutputStream("./Logger\\logger.txt"));
			requestBuilder = new RequestSpecBuilder().setBaseUri(getGlobalValues("baseUri"))
					.setContentType(ContentType.JSON).addFilter(RequestLoggingFilter.logRequestTo(stream))
					.addFilter(ResponseLoggingFilter.logResponseTo(stream)).build();
			return requestBuilder;
		}
		return requestBuilder;
	}

	public AddPlaceData getAddPlaceJson(String name, String language) {
		AddPlaceData addPlaceData = new AddPlaceData();
		addPlaceData.setLocation(getLocationData());
		addPlaceData.setAccuracy(50);
		addPlaceData.setName(name);
		addPlaceData.setPhone_number("(+91) 983 893 3937");
		addPlaceData.setAddress("29, side layout, cohen 09");
		addPlaceData.setTypes(getTypes());
		addPlaceData.setWebsite("http://google.com");
		addPlaceData.setLanguage(language);
		return addPlaceData;
	}

	public LocationData getLocationData() {
		LocationData location = new LocationData();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		return location;
	}

	public List<String> getTypes() {
		List<String> types = new ArrayList<String>();
		types.add("shoe park:");
		types.add("shop");
		return types;
	}

	public String getJsonData(String response, String key) {
		JsonPath js = new JsonPath(response);
		return js.get(key).toString();
	}

	public String getGlobalValues(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./Configurations\\config.properties");
		Properties property = new Properties();
		property.load(fis);
		return property.get(key).toString();
	}
	
	public String deletePlacePayload(String placeId)
	{
		return "{\r\n"
				+ "\r\n"
				+ "    \"place_id\":\"" + placeId  + "\"\r\n"
				+ "}";
	}

}
