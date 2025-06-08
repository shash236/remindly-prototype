package com.remindly.api.integrations.llm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolDto {
    private String name;
    private String description;
    private ToolParameterDto parameters;
}
