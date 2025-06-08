package com.remindly.api.lists.data;

import java.util.List;
import java.util.Map;

import com.remindly.api.integrations.llm.dto.ToolDto;
import com.remindly.api.integrations.llm.dto.ToolParameterDto;
import com.remindly.api.integrations.llm.dto.ToolPropertyDto;

public enum ListToolEnum {
    
    LIST_CREATE {
        @Override
        public ToolDto getToolDto() {
            return ToolDto.builder()
                    .name("list_create")
                    .description("Create a new list")
                    .parameters(ToolParameterDto.builder()
                            .type("object")
                            .properties(Map.of(
                                    "name", new ToolPropertyDto("string", "The name of the list"),
                                    "description", new ToolPropertyDto("string", "The description of the list")
                            ))
                            .required(List.of("name"))
                            .build())
                    .build();
        }
    };

    public abstract ToolDto getToolDto();

    public static List<ToolDto> getAllTools() {
        ListToolEnum[] tools = ListToolEnum.values();
        return List.of(tools).stream()
                .map(ListToolEnum::getToolDto)
                .toList();
    }

}
