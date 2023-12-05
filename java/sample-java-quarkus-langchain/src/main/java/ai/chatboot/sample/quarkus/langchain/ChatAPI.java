package ai.chatboot.sample.quarkus.langchain;

import ai.chatboot.model.ChatError;
import ai.chatboot.model.ChatRequest;
import ai.chatboot.model.ChatResponse;
import dev.langchain4j.model.chat.ChatLanguageModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiModelName;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import java.util.HashMap;
import java.util.Map;

import static dev.ai4j.openai4j.Model.GPT_3_5_TURBO;
import static java.time.Duration.ofSeconds;

@OpenAPIDefinition(
    info = @Info(title = "chat",
        version = "0.0.1", description = "Chat with the ChatBootAI",
        license = @License(
            name = "Apache 2.0",
            url = "http://www.apache.org/licenses/LICENSE-2.0.html")),
    tags = @Tag(name = "chat",
        description = "Chat with the ChatBootAI"))
@Path("/chat")
public class ChatAPI {

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Operation(operationId = "chat",
        summary = "Engage a conversation with ChatBootAI",
        description = "Post a prompt to ChatBootAI and receive a response")
    @Tag(name = "chat")
    @APIResponse(responseCode = "201", description = "Chat response",
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChatResponse.class))})
    @APIResponse(
        responseCode = "400", description = "HTTP Error Response",
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChatError.class))})
    @APIResponse(
        responseCode = "500", description = "HTTP Error Response",
        content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ChatError.class))})
    public Response chat(ChatRequest chatRequest) {

        ChatLanguageModel model = OpenAiChatModel.builder()
            .apiKey(ApiKeys.OPENAI_API_KEY)
            .modelName(OpenAiModelName.GPT_3_5_TURBO)
//            .modelName(chatRequest.getModel())
            .temperature(0.3)
//            .temperature(chatRequest.getTemperature().doubleValue())
            .timeout(ofSeconds(60))
            .logRequests(true)
            .logResponses(true)
            .build();

        String response = model.generate("Can you say hello in French?");
//        String response = model.generate(chatRequest.getMessages().get(0).getContent());

        return Response.ok().entity(response).build();
    }
}
