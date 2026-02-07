Feature: Validate the CRUD operation of the servicenow incident table api

Scenario: Validate user should able to fetch all records from the incident table api
Given user should set baseuri as "https://dev324941.service-now.com" of the servicenow api
And user should set basepath as "api/now/table" of the servicenow api
And user should set basic authenication username as "admin" and password as "e5!pRsPN%lH5"
When user should hit get method to retrieve all records from the incident table
Then user should see the status code should be "200"
And user should see the status line should be "OK"
And user should get response in the "JSON" format