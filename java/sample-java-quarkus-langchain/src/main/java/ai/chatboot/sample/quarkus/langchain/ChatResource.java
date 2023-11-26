package ai.chatboot.sample.quarkus.langchain;

import ai.chatboot.model.ChatRequest;
import ai.chatboot.model.HttpError;
import jakarta.annotation.Generated;
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
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
    info = @Info(
        title = "chat", version = "0.0.1", description = "Chat with the ChatBootAI",
        license = @License(name = "Apache 2.0", url = "http://www.apache.org/licenses/LICENSE-2.0.html")
    ),
    tags = @Tag(name = "chat", description = "Chat with the ChatBootAI")
)
@Path("/chat")
@Generated(value = "org.openapitools.codegen.languages.JavaJAXRSSpecServerCodegen", date = "2023-11-22T12:34:20.724274+01:00[Europe/Paris]")
public class ChatResource {

    @POST
    @Consumes({"application/json"})
    @Produces({"application/json"})
    @Operation(operationId = "chat", summary = "Chats with the bot", description = "Chats with the bot")
    @Tag(name = "chat")
    @APIResponses(value = {
        @APIResponse(responseCode = "400", description = "Default Response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HttpError.class))
        }),
        @APIResponse(responseCode = "500", description = "Default Response", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = HttpError.class))
        })
    })
    public Response chat(ChatRequest chatRequest) {
        return Response.ok().entity("magic!").build();
    }
}
