package resources;

public enum ApiResources {
	
	AddPlace("maps/api/place/add/json"),
	GetPlace("maps/api/place/get/json"),
	DeletePlace("maps/api/place/delete/json"),
	UpdatePlace("maps/api/place/update/json");
	
	private String apiResource;
	
	ApiResources(String apiResource)
	{
		this.apiResource = apiResource;
	}
	
	public String getApiResource()
	{
		return apiResource;
	}

}
