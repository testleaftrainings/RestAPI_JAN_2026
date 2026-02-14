Feature: Validate the CRUD operation of the servicenow incident table api

Background:
Given user should set baseuri as "https://dev324941.service-now.com" of the servicenow api
And user should set basepath as "api/now/table" of the servicenow api
And user should set basic authenication username as "admin" and password as "e5!pRsPN%lH5"

Scenario: Validate user should able to fetch all records from the incident table api
When user should hit get method to retrieve all records from the incident table
Then user should get the success response with following expected values
| statusCode | statusLine | responseFormat |
| 200        | OK         | JSON           |

Scenario: Validate user should able to fetch all records from the incident table api response in XML format
And user should set header key as "Accept" and value as "application/xml"
When user should hit get method to retrieve all records from the incident table
Then user should get the success response with following expected values
| statusCode | statusLine | responseFormat |
| 200        | OK         | XML            |

Scenario Outline: Validate user should create new record in the incident table
And user should set header key as "Content-Type" and value as "application/json"
And user should enter the required values in the request body
| shortDescription   | description   |
| <shortDescription> | <description> |
When user should hit post method to create new record in the incident table
Then user should get the success response with following expected values
| statusCode | statusLine | responseFormat |
| 201        | Created    | JSON           |
And user should see the "<shortDescription>" value in the short_description key in response

Examples:
| shortDescription        | description  |
| RESTAPISESSIONJAN2025-1 | description1 |
| RESTAPISESSIONJAN2025-2 | description2 |
| RESTAPISESSIONJAN2025-3 | description3 |
| RESTAPISESSIONJAN2025-4 | description4 |
| RESTAPISESSIONJAN2025-5 | description5 |