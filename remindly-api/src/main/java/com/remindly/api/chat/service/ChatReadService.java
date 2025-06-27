package com.remindly.api.chat.service;

import java.util.List;

import com.remindly.api.integrations.llm.data.MessageDto;

public interface ChatReadService {
    List<MessageDto> getAllMessages(Long chatId);
    List<MessageDto> getAllMessagesOptimized(Long chatId); // improved method for optimized retrieval
}
