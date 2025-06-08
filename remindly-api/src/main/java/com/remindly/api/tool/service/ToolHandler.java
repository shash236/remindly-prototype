package com.remindly.api.tool.service;

import com.remindly.api.integrations.llm.dto.ToolCallDto;

public interface ToolHandler {
   String getToolKey();
   String callTool(ToolCallDto toolCall);
}
