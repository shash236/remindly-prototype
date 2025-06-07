package com.remindly.api.chat.service;

import com.remindly.api.chat.service.dto.ChatCommand;
import com.remindly.api.chat.service.dto.ChatResult;
import com.remindly.api.chat.service.dto.InitiateChatCommand;
import com.remindly.api.chat.service.dto.InitiateChatResult;

public interface ChatService {
    InitiateChatResult initiateChat(InitiateChatCommand command);
    ChatResult respond(ChatCommand chatCommand);
}
