package com.remindly.api.chat.service.impl;

import org.springframework.stereotype.Service;

import com.remindly.api.chat.constants.RemindlyConstants;
import com.remindly.api.chat.domain.Chat;
import com.remindly.api.chat.domain.Message;
import com.remindly.api.chat.repository.ChatRepository;
import com.remindly.api.chat.repository.MessageRepository;
import com.remindly.api.chat.service.ChatReadService;
import com.remindly.api.chat.service.ChatService;
import com.remindly.api.chat.service.dto.ChatCommand;
import com.remindly.api.chat.service.dto.ChatResult;
import com.remindly.api.chat.service.dto.InitiateChatCommand;
import com.remindly.api.chat.service.dto.InitiateChatResult;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemindlyChatService implements ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final ChatReadService chatReadService;

    @Override
    public InitiateChatResult initiateChat(InitiateChatCommand command) {
        Chat chat = Chat.builder()
                .userId(command.getUserId())
                .channel(command.getChannel())
                .build();
        chatRepository.save(chat);
        Message initialMessage = Message.builder()
                .chatId(chat.getId())
                .content(RemindlyConstants.REMINDLY_ASSISTANT_PROMPT)
                .build();
    
        return InitiateChatResult.builder()
                .chatId(chat.getId())
                .message("Chat initiated successfully")
                .build();
    }

    @Override
    public ChatResult respond(ChatCommand command) {
        // Implementation logic for responding to a chat
        return new ChatResult();
    }
    
}
