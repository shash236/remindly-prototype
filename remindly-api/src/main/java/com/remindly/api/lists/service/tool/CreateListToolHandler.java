package com.remindly.api.lists.service.tool;

import org.springframework.stereotype.Service;

import com.remindly.api.integrations.llm.dto.ToolCallDto;
import com.remindly.api.lists.data.ListToolEnum;
import com.remindly.api.tool.service.ToolHandler;

@Service
public class CreateListToolHandler implements ToolHandler {

    @Override
    public String getToolKey() {
        return ListToolEnum.LIST_CREATE.name();
    }

    @Override
    public String callTool(ToolCallDto toolCall) {
        // Implement the logic to create a list using the provided tool call data
        // This is a placeholder implementation
        return "List created successfully with ID: 12345";
    }
    
}
