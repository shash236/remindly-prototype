package com.remindly.api.tool.service;

import java.util.Map;

import com.remindly.api.integrations.llm.data.ToolCallRequest;

public interface ToolHandler {
   String getToolKey();
   Map<String,Object> callTool(ToolCallRequest toolCallRequest);
}
