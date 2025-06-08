package com.remindly.api.integrations.llm.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GenerateCommand {
    private String model;
    private List<MessageDto> messages;
    private List<ToolDto> tools;
}
