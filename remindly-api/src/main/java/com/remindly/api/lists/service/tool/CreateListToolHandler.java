package com.remindly.api.lists.service.tool;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.remindly.api.integrations.llm.data.ToolCallRequest;
import com.remindly.api.lists.data.ListToolEnum;
import com.remindly.api.tool.service.ToolHandler;

@Service
public class CreateListToolHandler implements ToolHandler {

    @Override
    public String getToolKey() {
        return ListToolEnum.LIST_CREATE.name();
    }

    @Override
    public Map<String,Object> callTool(ToolCallRequest toolCallRequest) {
        // Implement the logic to create a list using the provided tool call data
        // This is a placeholder implementation
        return null;
    }
    
}
