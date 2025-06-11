package com.satc.integrador.ai.chatgpt;

import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.satc.integrador.ai.preference.dto.PreferenciaGetDto;
import com.satc.integrador.ai.studyplan.ExerciciosCall;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GptService {

    private final OpenAIClient client;

    public GptService(@Value("${openai.api.key}") String apiKey) {
        this.client = OpenAIOkHttpClient.builder()
                .apiKey(apiKey)
                .build();
    }

    public String gerarExercicios(PreferenciaGetDto preferencia, List<ExerciciosCall> exercicios) {
        String promptFinal = montarPromptCompleto(preferencia, exercicios);

        String systemMessage = "Voce é um auxiliador de plano de estudos e professor de linguagens, que deve gerar respostas estritamente no formato JSON, seguindo as regras fornecidas.";

        ChatCompletion chat = enviarParaGpt(systemMessage, promptFinal);

        return chat.choices().get(0).message().content().get();
    }

    private String montarPromptCompleto(PreferenciaGetDto preferencia, List<ExerciciosCall> exercicios) {
        String temasFormatados = String.join(", ", preferencia.temas());

        String descricaoExercicios = exercicios.stream()
                .map(ex -> String.format("%d exercícios do tipo %s", ex.qtExercicio, ex.tipo))
                .collect(Collectors.joining(" e "));

        int totalExercicios = exercicios.stream().mapToInt(ex -> ex.qtExercicio).sum();

        String promptBase = GptDados.inputPrincipal
                .replace("__BLOCO_DE_INSTRUCAO__", GptDados.inputTodosExercicios);

        return promptBase
                .replace("[NUMERO_DE_EXERCICIOS]", String.valueOf(totalExercicios))
                .replace("[IDIOMA]", preferencia.idioma())
                .replace("[TEMA]",
                        String.format("Foque nos seguintes temas: %s. Gere esta quantidade de exercícios: %s.",
                                temasFormatados, descricaoExercicios));
    }

    private ChatCompletion enviarParaGpt(String systemMessage, String userMessage) {
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addDeveloperMessage(systemMessage)
                .addUserMessage(userMessage)
                .model(ChatModel.GPT_4O_MINI)
                .build();

        return client.chat().completions().create(params);
    }
}