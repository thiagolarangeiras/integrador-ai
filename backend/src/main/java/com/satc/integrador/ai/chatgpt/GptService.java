package com.satc.integrador.ai.chatgpt;

import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.satc.integrador.ai.enums.TipoExercicios;
import com.satc.integrador.ai.preference.dto.PreferenciaGetDto;
import com.satc.integrador.ai.studyplan.ExerciciosCall;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

import java.util.List;

@Service
public class GptService {
    @Value("${openai.api.key}")
    private String apikey;

    private String sendInputChat() {
        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey("")
                .build();
        ResponseCreateParams params = ResponseCreateParams.builder()
                .input("Say this is a test")
                .model(ChatModel.GPT_3_5_TURBO)
                .build();
        Response response = client.responses().create(params);
        return response.toString();
    }

    private ChatCompletion sendInputExercicios(String script){
        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey(apikey)
                .build();
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addDeveloperMessage("Voce é um auxiliador de plano de estudos e professor de linguagens, que deve gerar respostas json para atividades")
                .addUserMessage(script)
                .model(ChatModel.GPT_3_5_TURBO)
                .build();
        ChatCompletion chatCompletion = client.chat().completions().create(params);
        return chatCompletion;
    }

    public String gerarExercicios(PreferenciaGetDto preferencia, List<ExerciciosCall> exercicios) {
        String scriptFinal = String.format(
                GptDados.cabecario,
                preferencia.idioma(),
                preferencia.tipoExercicio(),
                preferencia.temas(),
                preferencia.dificuldade(),
                preferencia.nivel()
        );

        for(ExerciciosCall exer : exercicios){
            switch (exer.tipo){
                case TipoExercicios.GRAMATICA_COMPLEMENTAR:
                    scriptFinal += String.format(GptDados.gramaticaComplementarContexto, exer.qtExercicio);
                    scriptFinal += GptDados.gramaticaComplementarJson;
                break;
                case TipoExercicios.GRAMATICA_ORDEM:
                    scriptFinal += String.format(GptDados.gramaticaOrdemContexto, exer.qtExercicio);
                    scriptFinal += GptDados.gramaticaOrdemJson;
                break;
                case TipoExercicios.VOCABULARIO_PARES:
                    scriptFinal += String.format(GptDados.vocabularioParesContexto, exer.qtExercicio);
                    scriptFinal += GptDados.vocabularioParesJson;
                break;
            }
        }
        scriptFinal += "GERAR ELES EM UM UNICO ARRAY";
        ChatCompletion chat = sendInputExercicios(scriptFinal);
        return chat.choices().get(0).message().content().get();
    }
}