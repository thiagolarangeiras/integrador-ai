package com.satc.integrador.ai.chatgpt;

public class GptDados {

  /**
   * Base do prompt, comum a todas as requisições.
   * Contém um placeholder __BLOCO_DE_INSTRUCAO__ que deve ser substituído
   * por uma das constantes de instrução específicas.
   */
  static final String inputPrincipal = """
      Você é um especialista em criar exercícios de idiomas. Sua única função é gerar uma resposta no formato JSON.

      # TAREFA
      Crie [NUMERO_DE_EXERCICIOS] exercícios sobre o tema "[TEMA]" para um estudante de [IDIOMA].

      # REGRAS GERAIS
      1. A resposta DEVE ser um único array JSON válido, começando com `[` e terminando com `]`.
      2. NÃO inclua nenhum texto, markdown, ou explicação fora do JSON. A resposta deve ser apenas o código JSON puro.
      3. Cada objeto no array deve seguir a estrutura: `{ "tipo": "...", "dados": { ... } }`.

      __BLOCO_DE_INSTRUCAO__

      Agora, gere os exercícios.
      """;

  static final String inputTodosExercicios = """
      # REGRAS DE ESTRUTURA PARA CADA TIPO DE EXERCÍCIO
      Sua resposta deve conter uma mistura dos exercícios solicitados, seguindo estritamente as estruturas abaixo para cada "tipo".

      ## Se "tipo" for "GRAMATICA_COMPLEMENTAR":
      A estrutura de "dados" DEVE ser:
      {
        "frase_completa": "A frase original e correta.",
        "frase_incompleta": "A mesma frase, mas com '___' no lugar da palavra removida.",
        "opcao_correta": "A palavra que foi removida.",
        "opcao_incorreta": ["Uma lista de palavras similares, mas incorretas."]
      }

      ## Se "tipo" for "GRAMATICA_ORDEM":
      A estrutura de "dados" DEVE ser:
      {
        "frase_completa": "A tradução correta em português BR.",
        "ordem_correta": ["um", "array", "de", "palavras", "na", "ordem", "certa", "na", "lingua", "escolhida"],
        "ordem_aleatoria": ["array", "na", "um", "de", "certa", "escolhida", "na", "palavras", "ordem", "lingua"]
      }

      ## Se "tipo" for "VOCABULARIO_PARES":
      A estrutura de "dados" DEVE ser:
      {
        "pares_esquerda": ["palavra1", "palavra2", "palavra3"],
        "pares_direita": ["tradução1", "tradução2", "tradução3"]
      }
      """;

  static final String inputGramaticaComplementar = """
      # INSTRUÇÕES PARA O EXERCÍCIO
      - O campo "tipo" DEVE ser a string "GRAMATICA_COMPLEMENTAR".
      - A estrutura do campo "dados" DEVE seguir exatamente este modelo:
        {
          "frase_completa": "A frase original e correta.",
          "frase_incompleta": "A mesma frase, mas com '___' no lugar da palavra removida.",
          "opcao_correta": "A palavra que foi removida.",
          "opcao_incorreta": ["Uma lista de palavras similares, mas incorretas."]
        }

      # EXEMPLO DE UM OBJETO
      {
        "tipo": "GRAMATICA_COMPLEMENTAR",
        "dados": {
          "frase_completa": "The cat is sleeping on the sofa.",
          "frase_incompleta": "The cat is ___ on the sofa.",
          "opcao_correta": "sleeping",
          "opcao_incorreta": ["sleeps", "slept", "sleep"]
        }
      }
      """;

  static final String inputGramaticaOrdem = """
      # INSTRUÇÕES PARA O EXERCÍCIO
      - O campo "tipo" DEVE ser a string "GRAMATICA_ORDEM".
      - A estrutura do campo "dados" DEVE seguir exatamente este modelo:
        {
          "frase_completa": "A frase original e correta.",
          "ordem_correta": ["um", "array", "de", "palavras", "na", "ordem", "certa"],
          "ordem_aleatoria": ["array", "um", "de", "certa", "na", "palavras", "ordem"]
        }

      # EXEMPLO DE UM OBJETO
      {
        "tipo": "GRAMATICA_ORDEM",
        "dados": {
          "frase_completa": "I need to buy some bread.",
          "ordem_correta": ["I", "need", "to", "buy", "some", "bread"],
          "ordem_aleatoria": ["buy", "bread", "I", "some", "to", "need"]
        }
      }
      """;

  static final String inputVocabularioPares = """
                  # INSTRUÇÕES PARA O EXERCÍCIO
                  - O campo "tipo" DEVE ser a string "VOCABULARIO_PARES".
                  - O campo "dados" DEVE seguir exatamente este modelo, onde o array "pares_direita" contém as traduções em português para as palavras do array "pares_esquerda", na mesma ordem.
                    {
                      "pares_esquerda": ["palavra1", "palavra2", "palavra3"],
                      "pares_direita": ["tradução1", "tradução2", "tradução3"]
                    }

                  # EXEMPLO DE UM OBJETO
                  {
                    "tipo": "VOCABULARIO_PARES",
                    "dados": {
                      "pares_esquerda": ["Car", "House", "Computer", "Water"],
                      "pares_direita": ["Carro", "Casa", "Computador", "Água"]
                    }
                  }
      """;
}