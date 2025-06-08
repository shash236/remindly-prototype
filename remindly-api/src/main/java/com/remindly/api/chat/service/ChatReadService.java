package com.remindly.api.chat.service;

import java.util.List;

import com.remindly.api.integrations.llm.dto.MessageDto;

public interface ChatReadService {
    List<MessageDto> getAllMessages(Long chatId);
}
