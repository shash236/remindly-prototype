package com.remindly.api.integrations.llm.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolCallDto {
    private String toolName;
    private Map<String, Object> toolArguments;
}
