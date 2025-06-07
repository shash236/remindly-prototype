package com.remindly.api.integrations.llm.service;

import com.remindly.api.integrations.llm.dto.GenerateCommand;
import com.remindly.api.integrations.llm.dto.GenerateResult;

public interface LLMService {
    GenerateResult generate(GenerateCommand command);
}
