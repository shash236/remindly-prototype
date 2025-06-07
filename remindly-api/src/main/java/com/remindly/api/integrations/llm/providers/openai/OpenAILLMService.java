package com.remindly.api.integrations.llm.providers.openai;

import org.springframework.stereotype.Service;

import com.remindly.api.integrations.llm.dto.GenerateCommand;
import com.remindly.api.integrations.llm.dto.GenerateResult;
import com.remindly.api.integrations.llm.service.LLMService;

@Service
public class OpenAILLMService implements LLMService{

    @Override
    public GenerateResult generate(GenerateCommand command) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'generate'");
    }
    
}
