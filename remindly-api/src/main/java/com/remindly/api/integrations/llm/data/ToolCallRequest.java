package com.remindly.api.integrations.llm.data;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolCallRequest {
    private String toolName;
    private String toolCallId;
    private Map<String, Object> toolArguments;
}
