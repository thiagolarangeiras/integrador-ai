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
      # INSTRUÇÕES PARA GERAÇÃO DE EXERCÍCIOS DE GRAMÁTICA (TIPO: COMPLETAR LACUNAS)
    
      - O campo "tipo" DEVE ser sempre a string: "GRAMATICA_COMPLEMENTAR".
      - O campo "dados" DEVE seguir EXATAMENTE esta estrutura JSON:
      
      {
        "frase_completa": "A frase completa, correta e gramaticalmente adequada em inglês.",
        "frase_incompleta": "A mesma frase, porém com a palavra-chave substituída por '___'.",
        "opcao_correta": "A palavra que foi removida da frase.",
        "opcao_incorreta": ["Uma lista de 3 palavras plausíveis no contexto, porém incorretas gramaticalmente."]
      }
      
      # REGRAS IMPORTANTES:
      1. A frase deve ser relevante para o aprendizado de gramática em inglês, abordando estruturas como:
         - Verb to be
         - Simple Present
         - Simple Past
         - Present Continuous
         - Future (Going to / Will)
         - Comparatives and Superlatives
         - Prepositions
         - Modals (can, must, should, etc.)
         - Possessives
         - Articles (a, an, the)
      2. A palavra removida deve ser um elemento gramatical chave (verbo, preposição, artigo, pronome, adjetivo comparativo, etc.).
      3. As alternativas incorretas devem ser convincentes para testar o aluno, sendo erros comuns, como:
         - Formas verbais erradas
         - Tempos verbais trocados
         - Preposições inadequadas
         - Artigos incorretos
      4. As frases devem ser naturais, contextualizadas e de uso comum no inglês.
      
      # EXEMPLO DE UM OBJETO GERADO:
      
      {
        "tipo": "GRAMATICA_COMPLEMENTAR",
        "dados": {
          "frase_completa": "She goes to school every day.",
          "frase_incompleta": "She ___ to school every day.",
          "opcao_correta": "goes",
          "opcao_incorreta": ["go", "going", "gone"]
        }
      }
      
      # SAÍDA ESPERADA:
      - Apenas o JSON do objeto, sem comentários, sem explicações adicionais.

      ## Se "tipo" for "GRAMATICA_ORDEM":
      # INSTRUÇÕES PARA GERAÇÃO DE EXERCÍCIOS DE ORDEM DE PALAVRAS (COM TRADUÇÃO EM PORTUGUÊS)
            
      - O campo "tipo" DEVE ser exatamente a string: "GRAMATICA_ORDEM".
            
      - O campo "dados" DEVE seguir rigorosamente este modelo JSON:
            
      {
        "frase_completa": "A frase completa, correta e natural EM PORTUGUÊS (tradução da frase em inglês).",
        "ordem_correta": ["Um array com as palavras na ordem correta DA FRASE EM INGLÊS."],
        "ordem_aleatoria": ["O mesmo array, mas com as palavras embaralhadas EM INGLÊS."]
      }
            
      # REGRAS IMPORTANTES:
      1. A frase deve ser clara, natural e correta EM PORTUGUÊS.
      2. A ordem correta e a ordem aleatória devem ser EM INGLÊS.
      3. O objetivo é que o aluno reorganize as palavras EM INGLÊS para formar a frase correta, usando a frase em PORTUGUÊS como referência.
      4. A ordem aleatória deve realmente desafiar o aluno, evitando que fique muito próxima da ordem correta, mas sem ser completamente confusa.
      5. As frases devem abranger estruturas gramaticais variadas, como:
         - Verb to be
         - Simple Present
         - Simple Past
         - Present Continuous
         - Future (Going to / Will)
         - Question formation (Yes/No questions e Wh-questions)
         - Negative sentences
         - Prepositions
         - Modals (can, must, should, etc.)
         - Articles e quantifiers
         - Possessive forms
      6. As palavras devem estar separadas corretamente nos arrays (sem pontuação dentro do array, apenas na frase completa em português, se necessário).
      7. A pontuação final (como ponto) NÃO aparece nos arrays, apenas na frase completa EM PORTUGUÊS.
            
      # FORMATO DA SAÍDA:
      - Apenas o JSON do objeto, exatamente no padrão, sem comentários, sem explicações adicionais.
            
      # EXEMPLO DE UM OBJETO GERADO:
            
      {
        "tipo": "GRAMATICA_ORDEM",
        "dados": {
          "frase_completa": "Ela vai visitar a avó dela amanhã.",
          "ordem_correta": ["She", "is", "going", "to", "visit", "her", "grandmother", "tomorrow"],
          "ordem_aleatoria": ["visit", "tomorrow", "her", "going", "She", "is", "to", "grandmother"]
        }
      }
               
      ## Se "tipo" for "VOCABULARIO_PARES":
      # INSTRUÇÕES PARA GERAÇÃO DE EXERCÍCIOS DE VOCABULÁRIO – ASSOCIAÇÃO DE PARES
            
      - O campo "tipo" DEVE ser exatamente a string: "VOCABULARIO_PARES".
            
      - O campo "dados" DEVE seguir rigorosamente este modelo JSON:
            
      {
        "pares_esquerda": ["Um array de palavras em INGLÊS."],
        "pares_direita": ["Um array de TRADUÇÕES EM PORTUGUÊS, na mesma ordem das palavras em inglês."]
      }
            
      # REGRAS IMPORTANTES:
      1. O array "pares_esquerda" DEVE conter palavras em INGLÊS.
      2. O array "pares_direita" DEVE conter as traduções em PORTUGUÊS, correspondendo exatamente à mesma ordem do array da esquerda.
      3. As palavras devem ser de uso comum, contextualizadas, relevantes para o aprendizado de inglês, podendo pertencer a categorias como:
         - Objetos do dia a dia
         - Profissões
         - Verbos comuns
         - Adjetivos
         - Cores, números, família
         - Alimentação
         - Partes do corpo
         - Locais da cidade
         - Meios de transporte
         - Clima e tempo
      4. Não usar frases ou expressões completas — apenas palavras individuais.
      5. Evitar falsos cognatos, erros de tradução ou palavras excessivamente técnicas.
      6. As traduções devem ser diretas, naturais e precisas para o contexto mais comum da palavra.
      7. Use apenas letras sem formatação especial (não usar itálico, negrito, aspas dentro dos arrays).
            
      # FORMATO DA SAÍDA:
      - Apenas o JSON do objeto, exatamente no padrão, sem comentários ou explicações adicionais.
            
      # EXEMPLO DE UM OBJETO GERADO:
            
      {
        "tipo": "VOCABULARIO_PARES",
        "dados": {
          "pares_esquerda": ["Car", "House", "Computer", "Water"],
          "pares_direita": ["Carro", "Casa", "Computador", "Água"]
        }
      }       
      """;

  static final String inputGramaticaComplementar = """
     # INSTRUÇÕES PARA GERAÇÃO DE EXERCÍCIOS DE GRAMÁTICA (TIPO: COMPLETAR LACUNAS)
    
    - O campo "tipo" DEVE ser sempre a string: "GRAMATICA_COMPLEMENTAR".
    - O campo "dados" DEVE seguir EXATAMENTE esta estrutura JSON:
    
    {
      "frase_completa": "A frase completa, correta e gramaticalmente adequada em inglês.",
      "frase_incompleta": "A mesma frase, porém com a palavra-chave substituída por '___'.",
      "opcao_correta": "A palavra que foi removida da frase.",
      "opcao_incorreta": ["Uma lista de 3 palavras plausíveis no contexto, porém incorretas gramaticalmente."]
    }
    
    # REGRAS IMPORTANTES:
    1. A frase deve ser relevante para o aprendizado de gramática em inglês, abordando estruturas como:
       - Verb to be
       - Simple Present
       - Simple Past
       - Present Continuous
       - Future (Going to / Will)
       - Comparatives and Superlatives
       - Prepositions
       - Modals (can, must, should, etc.)
       - Possessives
       - Articles (a, an, the)
    2. A palavra removida deve ser um elemento gramatical chave (verbo, preposição, artigo, pronome, adjetivo comparativo, etc.).
    3. As alternativas incorretas devem ser convincentes para testar o aluno, sendo erros comuns, como:
       - Formas verbais erradas
       - Tempos verbais trocados
       - Preposições inadequadas
       - Artigos incorretos
    4. As frases devem ser naturais, contextualizadas e de uso comum no inglês.
    
    # EXEMPLO DE UM OBJETO GERADO:
    
    {
      "tipo": "GRAMATICA_COMPLEMENTAR",
      "dados": {
        "frase_completa": "She goes to school every day.",
        "frase_incompleta": "She ___ to school every day.",
        "opcao_correta": "goes",
        "opcao_incorreta": ["go", "going", "gone"]
      }
    }
    
    # SAÍDA ESPERADA:
    - Apenas o JSON do objeto, sem comentários, sem explicações adicionais.
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