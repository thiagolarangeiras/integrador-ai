package com.satc.integrador.ai.chatgpt;

public class GptDados {
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
}
