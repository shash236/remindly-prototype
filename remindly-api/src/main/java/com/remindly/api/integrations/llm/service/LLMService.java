package com.remindly.api.integrations.llm.service;

import com.remindly.api.integrations.llm.data.GenerateCommand;
import com.remindly.api.integrations.llm.data.GenerateResult;

public interface LLMService {
    GenerateResult generate(GenerateCommand command);
}
