package it.digrazia.openai.model.request;

public record CompletionRequest(String model, String prompt,
                                double temperature, int max_tokens) {
	public static CompletionRequest withDaVinci(String prompt) {

		return new CompletionRequest("text-davinci-003",
				prompt, 0.7, 100);
	}

	public static CompletionRequest withCurie(String prompt) {

		return new CompletionRequest("text-curie-001",
				prompt, 0.7, 100);
	}

	public static CompletionRequest withBabbage(String prompt) {

		return new CompletionRequest("text-babbage-001",
				prompt, 0.7, 100);
	}

	public static CompletionRequest withAda(String prompt) {

		return new CompletionRequest("text-ada-001",
				prompt, 0.7, 100);
	}
	
}
