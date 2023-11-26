package ai.chatboot.sample;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class ChatResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .when().post("/chat")
            .then()
            .statusCode(200)
            .body(is("magic!"));
    }
}
