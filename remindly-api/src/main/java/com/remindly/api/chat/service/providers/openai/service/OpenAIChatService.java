package com.remindly.api.chat.service.providers.openai.service;

import org.springframework.beans.factory.annotation.Value;

import com.remindly.api.chat.service.ChatService;

public class OpenAIChatService implements ChatService {

    
    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Override
    public String respond(String userMessage) {
        // Implementation for OpenAI chat response
        // This is a placeholder; actual implementation would involve calling OpenAI's API
        return "Response from OpenAI for message: " + userMessage;
    }
    
}
