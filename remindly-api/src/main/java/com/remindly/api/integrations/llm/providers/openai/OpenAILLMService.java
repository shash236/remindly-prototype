package com.remindly.api.integrations.llm.providers.openai;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.remindly.api.integrations.llm.data.GenerateCommand;
import com.remindly.api.integrations.llm.data.GenerateResult;
import com.remindly.api.integrations.llm.providers.openai.data.OpenAiChatRequest;
import com.remindly.api.integrations.llm.providers.openai.data.OpenAiChatResponse;
import com.remindly.api.integrations.llm.service.LLMService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OpenAILLMService implements LLMService {

    @Value("${openai.api.url}")
    private String openAiApiUrl;

    @Value("${openai.api.key}")
    private String openAiApiKey;

    private final RestTemplate restTemplate;

    @Override
    public GenerateResult generate(GenerateCommand command) {
        OpenAiChatRequest chatRequest = createChatRequest(command);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(openAiApiKey);
        HttpEntity<OpenAiChatRequest> entity = new HttpEntity<>(chatRequest, headers);
        try {
            ResponseEntity<OpenAiChatResponse> response = restTemplate.postForEntity(
                    openAiApiUrl,
                    entity,
                    OpenAiChatResponse.class);
            if (response.getStatusCode().isError()) {
                throw new RuntimeException("OpenAI API returned an error: " + response.getStatusCode()); // TODO: Handle specific error cases
            }
            OpenAiChatResponse chatResponse = response.getBody();
            return convertToGenerateResult(chatResponse);
        } catch (HttpStatusCodeException e) {
            throw new RuntimeException("OpenAI API error: " + e.getResponseBodyAsString(), e);
        }
    }

    private OpenAiChatRequest createChatRequest(GenerateCommand command) {
        return OpenAiChatRequest.builder()
                .model(command.getModel())
                .messages(command.getMessages().stream()
                        .map(msg -> OpenAiChatRequest.Message.builder()
                                .role(msg.getRole())
                                .content(msg.getContent().toString())
                                .build())
                        .toList())
                .tools(command.getTools() != null ? command.getTools().stream()
                        .map(tool -> OpenAiChatRequest.OpenAiTool.builder()
                                .type("function")
                                .function(OpenAiChatRequest.Function.builder()
                                        .name(tool.getName())
                                        .description(tool.getDescription())
                                        .parameters(OpenAiChatRequest.Parameters.builder()
                                                .type(tool.getParameters().getType())
                                                .properties(tool.getParameters().getProperties().entrySet().stream()
                                                        .collect(Collectors.toMap(
                                                                Map.Entry::getKey,
                                                                e -> OpenAiChatRequest.Property.builder()
                                                                        .type(e.getValue().getType())
                                                                        .description(e.getValue().getDescription())
                                                                        .build())))
                                                .requiredFields(tool.getParameters().getRequired())
                                                .build())
                                        .build())
                                .build())
                        .toList() : null)
                .toolChoice("auto")
                .temperature(0.7)
                .maxTokens(1000)
                .build();
    }


    private GenerateResult convertToGenerateResult(OpenAiChatResponse chatResponse) {
        return null; // TODO: Implement conversion from OpenAiChatResponse to GenerateResult
    }

}
