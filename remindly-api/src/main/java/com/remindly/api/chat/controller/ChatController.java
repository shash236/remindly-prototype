package com.remindly.api.chat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.remindly.api.chat.dto.request.ChatRequest;
import com.remindly.api.chat.dto.request.InitiateChatRequest;
import com.remindly.api.chat.dto.response.ChatResponse;
import com.remindly.api.chat.dto.response.InitiateChatResponse;
import com.remindly.api.chat.service.ChatService;
import com.remindly.api.chat.service.dto.ChatCommand;
import com.remindly.api.chat.service.dto.ChatResult;
import com.remindly.api.chat.service.dto.InitiateChatCommand;
import com.remindly.api.chat.service.dto.InitiateChatResult;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/chat")
@RequiredArgsConstructor
public class ChatController {
    
    private final ChatService chatService;
    
    @PostMapping("/initiate")
    public InitiateChatResponse initiateChat(@RequestBody InitiateChatRequest initiateChatRequest) {
        Long userId = 1L; // Placeholder for user ID, should be replaced with actual user context
        InitiateChatResult result = chatService.initiateChat(InitiateChatCommand.builder()
                .userId(userId)
                .channel(initiateChatRequest.getChannel())
                .build());
        return InitiateChatResponse.builder()
                .chatId(result.getChatId())
                .message(result.getMessage())
                .build();
    }

    @PostMapping("/{chatId}/respond")
    public ChatResponse respond(@PathVariable Long chatId, @RequestBody ChatRequest chatRequest) {
        ChatResult result = chatService.respond(ChatCommand.builder()
                .chatId(chatId)
                .message(chatRequest.getMessage())
                .build());
        return ChatResponse.builder()
                .messageId(result.getMessageId())
                .message(result.getMessage())
                .build();
    }

    @GetMapping("/{chatId}/messages")
    public List<ChatResponse> getChatMessages(@PathVariable Long chatId) {
        // Placeholder for fetching chat messages logic
        // This method will retrieve messages from a specific chat conversation
        return new ArrayList<>();
    }
    
}
