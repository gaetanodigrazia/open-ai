package it.digrazia.openai.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.digrazia.openai.config.OpenAiApiClient;
import it.digrazia.openai.model.enums.GPT3Models;
import it.digrazia.openai.model.enums.OpenAiServices;
import it.digrazia.openai.model.request.CompletionRequest;
import it.digrazia.openai.model.response.CompletionResponse;
import it.digrazia.openai.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatGPTServiceImpl implements ChatGPTService {
    private final ObjectMapper jsonMapper;
    private final OpenAiApiClient client;

    @Autowired
    public ChatGPTServiceImpl(OpenAiApiClient openAiApiClient){
        this.client = openAiApiClient;
        this.jsonMapper = new ObjectMapper();
    }

    @Override
    public String sendCompletionRequest(CompletionRequest completionRequest) throws Exception {
        CompletionRequest completion = this.selectBaseModel(completionRequest);

        var postBodyJson = jsonMapper.writeValueAsString(completion);
        var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiServices.GPT_3_COMPLETIONS);
        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
        return completionResponse.firstAnswer().orElseThrow();
    }
    @Override
    public CompletionResponse sendCompletionRequestGetResponse(CompletionRequest completionRequest) throws Exception {
        CompletionRequest completion = this.selectBaseModel(completionRequest);

        var postBodyJson = jsonMapper.writeValueAsString(completion);
        var responseBody = client.postToOpenAiApi(postBodyJson, OpenAiServices.GPT_3_COMPLETIONS);
        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
        return completionResponse;
    }
    final CompletionRequest selectBaseModel(CompletionRequest completionRequest) {
        if(completionRequest.model()==null){
           return CompletionRequest.withDaVinci(completionRequest.prompt());
        }
        switch (completionRequest.model().toString()) {
            case "davinci" -> {
                return CompletionRequest.withDaVinci(completionRequest.prompt());
            }
            case "curie" -> {
                return CompletionRequest.withCurie(completionRequest.prompt());
            }
            case "babbage" -> {
                return CompletionRequest.withBabbage(completionRequest.prompt());
            }
            case "ada" -> {
                return CompletionRequest.withAda(completionRequest.prompt());
            }
        }
        return null;
    }

}
