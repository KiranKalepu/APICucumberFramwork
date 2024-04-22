Feature: Validating the google place Apis

@AddPlace
Scenario Outline: Add a place using the AddPlace Api
Given addPlace api payload using "<name>" and "<language>"
When user hit the "AddPlace" api using valid data with "POST" http request
Then the api call status is 200
And the "status" response should be "OK"
And the "scope" response should be "APP"

Examples:
| name 	 | language 		|
#| tester | test lanuage |
#| tezo	 | hindi				|
| kiran	 | Telugu				|

@GetPlace
Scenario: Get the details of the place using the GetPlace Api
Given getPlace api
When user hit the "GetPlace" api using valid data with "GET" http request
Then the api call status is 200

@DeletePlace
Scenario: Delete the place using the DeletePlace Api
Given deletePlace api payload
When user hit the "DeletePlace" api using valid data with "POST" http request
Then the api call status is 200
And the "status" response should be "OK"