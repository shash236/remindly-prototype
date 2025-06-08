package com.remindly.api.integrations.llm.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ToolParameterDto {
    private String type;
    private Map<String,ToolPropertyDto> properties;
    private List<String> required;
}
