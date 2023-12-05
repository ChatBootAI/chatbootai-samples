package ai.chatboot.sample;

import ai.chatboot.model.ChatRequest;
import ai.chatboot.model.ChatRequestMessage;
import io.quarkus.test.junit.QuarkusTest;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;

import static org.hamcrest.CoreMatchers.is;

import org.junit.jupiter.api.Test;

@QuarkusTest
public class ChatAPITest {

    @Test
    public void testHelloEndpoint() {
        ChatRequestMessage msgOne = new ChatRequestMessage().content("content one");
        ChatRequestMessage msgTwo = new ChatRequestMessage().content("content two");
        ChatRequest chatRequest = new ChatRequest().model("model").addMessagesItem(msgOne).addMessagesItem(msgTwo);

        given()
            .body(chatRequest)
            .accept(ContentType.JSON)
            .contentType(ContentType.JSON)
            .when().post("/chat")
            .then()
            .statusCode(200)
            .body(is("model"));
    }
}
