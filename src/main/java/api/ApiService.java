package api;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static properties.PropertyKey.API_URL;
import static properties.PropertyReader.getProperty;

public abstract class ApiService {

    public RequestSpecification getRequestSpecification() {
        return new RequestSpecBuilder()
                .setBaseUri(getProperty(API_URL))
                .build()
                .log().all();
    }

    RequestSpecification requestSpec = getRequestSpecification();

    protected Response performGetRequest(Endpoint endpoint) {
        return given(requestSpec)
                .get(endpoint.getPath());
    }

    protected Response performPostRequest(Endpoint endpoint, String requestBody) {
        return given(requestSpec)
                .body(requestBody)
                .post(endpoint.getPath());
    }

    protected Response performDeleteRequest(Endpoint endpoint) {
        return given(requestSpec)
                .delete(endpoint.getPath());
    }
}