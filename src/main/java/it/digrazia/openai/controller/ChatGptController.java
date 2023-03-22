package it.digrazia.openai.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import it.digrazia.openai.config.OpenAiApiClient;
import it.digrazia.openai.model.request.CompletionRequest;
import it.digrazia.openai.model.response.CompletionResponse;
import it.digrazia.openai.service.ChatGPTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ChatGptController {

	@Autowired private ObjectMapper jsonMapper;
	@Autowired private OpenAiApiClient client;

	@Autowired
	ChatGPTService chatGPTService;
	@PostMapping(path = "/response/complete")
	@ResponseBody
	public CompletionResponse completionRequestWithResponse(@RequestBody CompletionRequest completionRequest) throws Exception {
		return chatGPTService.sendCompletionRequestGetResponse(completionRequest);
	}

	@PostMapping(path = "/response/answer")
	@ResponseBody
	public String completionRequestOnlyAnswer(@RequestBody CompletionRequest completionRequest) throws Exception {
		return chatGPTService.sendCompletionRequest(completionRequest);
	}
	
}
