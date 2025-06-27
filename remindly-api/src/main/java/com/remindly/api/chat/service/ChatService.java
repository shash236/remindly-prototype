package com.remindly.api.chat.service;

import com.remindly.api.chat.service.data.ChatCommand;
import com.remindly.api.chat.service.data.ChatResult;
import com.remindly.api.chat.service.data.InitiateChatCommand;
import com.remindly.api.chat.service.data.InitiateChatResult;

public interface ChatService {
    InitiateChatResult initiateChat(InitiateChatCommand command);
    ChatResult respond(ChatCommand chatCommand);
}
