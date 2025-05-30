package com.satc.integrador.ai.studyPlan;

import com.openai.models.chat.completions.ChatCompletion;
import com.openai.models.chat.completions.ChatCompletionCreateParams;
import com.satc.integrador.ai.enums.TipoExercicios;
import com.satc.integrador.ai.preference.dto.PreferenciaGetDto;
import org.springframework.stereotype.Service;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

import java.util.List;

@Service
public class OpenAiExample {
    static final String cabecario = "Gerar um plano de estudo com base nesse dados:\n" +
            "\tidioma = %s;\n" +
            "\ttipoExercicio = %s;\n" +
            "\ttemas = %s;\n" +
            "\tdificuldade que tem = %s;\n" +
            "\tnivel = %s;\n";

    static final String gramaticaComplementarContexto =
            "Retornar %d exercios de:\n" +
            "Completar uma frase : Gerar uma frase na lingua selecionada e remover uma palavra dela e gerar similares para aquela palavra\n";

    static final String gramaticaComplementarJson =
            "exemplo de json de retorno:\n"+
            "{\n" +
            "    \"tipo\": \"GRAMATICA_COMPLEMENTAR\",\n" +
            "    \"dados\": {\n" +
            "        \"frase_completa\": \"Social media platforms influence how people communicate.\",\n" +
            "        \"frase_incompleta\": \"Social media platforms ... how people communicate.\",\n" +
            "        \"opcao_correta\": \"influence\",\n" +
            "        \"opcao_incorreta\": [\"create\", \"follow\", \"interrupt\"]\n" +
            "    }\n" +
            "}\n";

    static final String gramaticaOrdemContexto =
            "Retornar %d exercios de:\n" +
            "Organiazar uma frase: Gerar uma frase na lingua selecionada e separar ela em um array e gerar outro array com as mesmas palavras mas aleatorios\n";

    static final String gramaticaOrdemJson =
            "exemplo de json de retorno:\n"+
            "{\n" +
            "    \"tipo\": \"GRAMATICA_ORDEM\",\n" +
            "    \"dados\": {\n" +
            "        \"frase_completa\": \"Social media platforms influence how people communicate.\",\n" +
            "        \"ordem_correta\": [\"Social\", \"media\", \"platforms\", \"influence\", \"how\", \"people\", \"communicate.\"],\n" +
            "        \"ordem_aleatoria\": [\"communicate.\", \"media\", \"Social\", \"influence\", \"platforms\", \"how\", \"people\"]\n" +
            "    }\n" +
            "}\n";

    static final String vocabularioParesContexto =
            "Retornar %d exercios de:\n" +
            "Vocabulario Pares: Gerar uma lista de palavras na lingua selecionada e uma lista com suas traduções em portugues em outra lista na mesma ordem\n";

    static final String vocabularioParesJson =
            "exemplo de json de retorno:\n"+
            "{\n" +
            "    \"tipo\": \"VOCABULARIO_PARES\",\n" +
            "    \"dados\": {\n" +
            "        \"pares_esquerda\": [\"Social\", \"media\", \"platforms\", \"influence\", \"how\", \"people\", \"communicate\"],\n" +
            "        \"pares_direita\": [\"Social\", \"midia\", \"plataforma\", \"influencia\", \"como\", \"pessoas\", \"comunicam\"],\n" +
            "    }\n" +
            "}\n";

    public static String gerarExercicios(PreferenciaGetDto preferencia, List<ExerciciosCall> exercicios) {
        String scriptFinal = String.format(
                cabecario,
                preferencia.idioma(),
                preferencia.tipoExercicio(),
                preferencia.temas(),
                preferencia.dificuldade(),
                preferencia.nivel()
        );

        for(ExerciciosCall exer : exercicios){
            switch (exer.tipo){
                case TipoExercicios.GRAMATICA_COMPLEMENTAR:
                    scriptFinal += String.format(gramaticaComplementarContexto, exer.qtExercicio);
                    scriptFinal += gramaticaComplementarJson;
                break;
                case TipoExercicios.GRAMATICA_ORDEM:
                    scriptFinal += String.format(gramaticaOrdemContexto, exer.qtExercicio);
                    scriptFinal += gramaticaOrdemJson;
                break;
                case TipoExercicios.VOCABULARIO_PARES:
                    scriptFinal += String.format(vocabularioParesContexto, exer.qtExercicio);
                    scriptFinal += vocabularioParesJson;
                break;
            }
        }
        scriptFinal += "GERAR ELES EM UM UNICO ARRAY";

        OpenAIClient client = OpenAIOkHttpClient.builder()
                .apiKey("")
                .build();
        ChatCompletionCreateParams params = ChatCompletionCreateParams.builder()
                .addDeveloperMessage("Voce é um auxiliador de plano de estudos e professor de linguagens, que deve gerar respostas json para atividades")
                .addUserMessage(scriptFinal)
                .model(ChatModel.GPT_3_5_TURBO)
                .build();
        ChatCompletion chatCompletion = client.chat().completions().create(params);
        String respostaJson = chatCompletion.choices().get(0).message().content().get();
        return respostaJson;
    }

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