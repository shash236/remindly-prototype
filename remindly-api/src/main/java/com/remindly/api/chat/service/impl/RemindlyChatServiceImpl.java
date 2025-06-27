package com.remindly.api.chat.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.remindly.api.chat.data.RemindlyConstants;
import com.remindly.api.chat.data.RoleEnum;
import com.remindly.api.chat.domain.Chat;
import com.remindly.api.chat.domain.Message;
import com.remindly.api.chat.repository.ChatRepository;
import com.remindly.api.chat.repository.MessageRepository;
import com.remindly.api.chat.service.ChatReadService;
import com.remindly.api.chat.service.ChatService;
import com.remindly.api.chat.service.data.ChatCommand;
import com.remindly.api.chat.service.data.ChatResult;
import com.remindly.api.chat.service.data.InitiateChatCommand;
import com.remindly.api.chat.service.data.InitiateChatResult;
import com.remindly.api.integrations.llm.data.GenerateCommand;
import com.remindly.api.integrations.llm.data.GenerateResult;
import com.remindly.api.integrations.llm.data.MessageDto;
import com.remindly.api.integrations.llm.data.ToolDto;
import com.remindly.api.integrations.llm.service.LLMService;
import com.remindly.api.lists.data.ListToolEnum;
import com.remindly.api.tool.service.ToolHandler;
import com.remindly.api.tool.service.ToolHandlerRegistry;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RemindlyChatServiceImpl implements ChatService {

    private final ChatRepository chatRepository;
    private final MessageRepository messageRepository;
    private final ChatReadService chatReadService;
    private final LLMService llmService;
    private final ToolHandlerRegistry toolHandlerRegistry;

    @Override
    public InitiateChatResult initiateChat(InitiateChatCommand command) {
        Chat chat = Chat.builder()
                .userId(command.getUserId())
                .channel(command.getChannel())
                .build();
        chatRepository.save(chat);
        Message initialMessage = Message.builder()
                .chatId(chat.getId())
                .role(RoleEnum.ASSISTANT.toString().toLowerCase())
                .content(Map.of("content",RemindlyConstants.REMINDLY_ASSISTANT_PROMPT))
                .isDisplayable(false)
                .build();
        Message firstAssistantMessage = Message.builder()
                .chatId(chat.getId())
                .role(RoleEnum.ASSISTANT.toString().toLowerCase())
                .content(Map.of("content","Hi there! How can I assist you today?"))
                .isDisplayable(true)
                .build();
        messageRepository.saveAll(List.of(initialMessage, firstAssistantMessage));
        return InitiateChatResult.builder()
                .chatId(chat.getId())
                .message(firstAssistantMessage.getContent().toString())
                .build();
    }

    @Override
    public ChatResult respond(ChatCommand chatCommand) {
        List<MessageDto> messageDtos = chatReadService.getAllMessages(chatCommand.getChatId());
        Message userMessage = Message.builder()
                .chatId(chatCommand.getChatId())
                .content(Map.of("content",chatCommand.getMessage()))
                .role(RoleEnum.USER.toString().toLowerCase())
                .isDisplayable(true)
                .build();
        messageRepository.save(userMessage);
        messageDtos.add(getMessageDto(userMessage));
        List<ToolDto> tools = ListToolEnum.getAllTools();
        GenerateCommand generateCommand = GenerateCommand.builder()
                .model(RemindlyConstants.MODEL_NAME)
                .messages(messageDtos)
                .tools(tools)
                .build();
        GenerateResult generateResult = llmService.generate(generateCommand);
        Message llmMessage = Message.builder()
                .chatId(chatCommand.getChatId())
                .content(generateResult.getMessage().getContent())
                .role(generateResult.getMessage().getRole())
                .isDisplayable(true)
                .build();
        messageRepository.save(llmMessage);
        boolean isToolCall = generateResult.isToolCall();
        while (isToolCall) {
            String toolKey = generateResult.getToolCallRequest().getToolName();
            ToolHandler toolHandler = toolHandlerRegistry.getToolHandler(toolKey);
            Map<String,Object> toolResult = toolHandler.callTool(generateResult.getToolCallRequest());
            Message toolMessage = Message.builder()
                    .chatId(chatCommand.getChatId())
                    .content(toolResult)
                    .role(RoleEnum.TOOL.toString().toLowerCase())
                    .isDisplayable(true)
                    .build();
            messageRepository.save(toolMessage);
            messageDtos.add(getMessageDto(toolMessage));
            generateCommand.setMessages(messageDtos);
            generateResult = llmService.generate(generateCommand);
            isToolCall = generateResult.isToolCall();
        }
        Message finalMessage = Message.builder()
                .chatId(chatCommand.getChatId())
                .content(generateResult.getMessage().getContent())
                .role(generateResult.getMessage().getRole())
                .isDisplayable(true)
                .build();
        messageRepository.save(finalMessage);
        return ChatResult.builder()
                .messageId(finalMessage.getId())
                .message(finalMessage.getContent().get("content").toString())
                .build();
    }

    private MessageDto getMessageDto(Message message) {
        return MessageDto.builder()
                .content(message.getContent())
                .role(message.getRole())
                .build();
    }
    
}
