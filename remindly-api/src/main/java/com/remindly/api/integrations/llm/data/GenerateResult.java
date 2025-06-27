package com.remindly.api.integrations.llm.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateResult {
    private MessageDto message;
    private boolean isToolCall;
    private ToolCallRequest toolCallRequest;
}
