## API basic information

* HTTP-baseUrl: https://api.bitmake.com.
* Requests and responses use JSON.
* All timestamps are UNIX time in milliseconds.

## HTTP return codes

* HTTP 4XX return codes are used for malformed requests; the issue is on the sender's side.
* HTTP 403 return code is used when the WAF Limit (Web Application Firewall) has been violated.
* HTTP 429 return code is used when breaking a request rate limit.
* HTTP 5XX return codes are used for internal errors; the issue is on Bitmake's side. It is important to NOT treat this as a failure operation; the execution status is UNKNOWN and could have been a success.

## Basic information of the interface

* The interface of the GET method, the parameters must be sent in the query string.
* For the interface of the POST method, except for the specified public parameters that need to be sent in the query string, the parameters defined in the interface document must be sent in json format.