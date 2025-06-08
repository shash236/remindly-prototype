package com.remindly.api.tool.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public class ToolHandlerRegistry {
    final Map<String, ToolHandler> toolHandlersMap;

    public ToolHandlerRegistry(List<ToolHandler> toolHandlers) {
        toolHandlersMap = new HashMap<>();
        toolHandlers.forEach(handler -> {
            toolHandlersMap.put(handler.getToolKey(), handler);
        });
    }

    public ToolHandler getToolHandler(String toolKey) {
        return Optional.ofNullable(toolHandlersMap.get(toolKey))
                .orElseThrow(() -> new IllegalArgumentException("No tool handler found for key: " + toolKey));
    }
}
