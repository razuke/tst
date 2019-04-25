package mvn.tst;

import static org.hamcrest.Matchers.is;

import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


public class AppTest {
	String baseUrl = "https://postman-echo.com/get?foo1=bar1&foo2=bar2";
	
	private Response defaultGetRequest() {
		return given().relaxedHTTPSValidation()
				.param("foo1", "bar1")
				.param("foo2", "bar2")
				.when()
				.get(baseUrl);
	}
	
	
	@Before
    public void beforeEach() {
		System.out.println("Starting new Test");
	}
	
	@Test
	public void validateStatusCode() {
			defaultGetRequest()
			.then()
			.statusCode(202);
	}
	
	@Test
	public void validateContentType() {
			defaultGetRequest()
			.then()
			.contentType(ContentType.JSON);
	}
	
	@Test
	public void validateHost() {
			defaultGetRequest()
			.then()
			.body("headers.host", is("postman-echo.com"));
	}
}
