package com.satc.integrador.ai.studyPlan;

import org.springframework.stereotype.Service;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

@Service
public class OpenAiExample {
    public static void chat() {
        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey("")
                .build();
        ResponseCreateParams params = ResponseCreateParams.builder()
                .input("Say this is a test")
                .model(ChatModel.GPT_3_5_TURBO)
                .build();
        Response response = client.responses().create(params);
        System.out.println(response.toString());
    }
}