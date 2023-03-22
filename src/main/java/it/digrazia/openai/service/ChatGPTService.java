package it.digrazia.openai.service;

import it.digrazia.openai.model.request.CompletionRequest;
import it.digrazia.openai.model.response.CompletionResponse;
public interface ChatGPTService {
    String sendCompletionRequest(CompletionRequest completionRequest) throws Exception;

    CompletionResponse sendCompletionRequestGetResponse(CompletionRequest completionRequest) throws Exception;
}
