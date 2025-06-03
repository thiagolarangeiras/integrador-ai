package com.satc.integrador.ai.studyplan;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.satc.integrador.ai.enums.TipoExercicios;
import com.satc.integrador.ai.exercicios.*;
import com.satc.integrador.ai.preference.PreferenciaService;
import com.satc.integrador.ai.preference.dto.PreferenciaGetDto;
import com.satc.integrador.ai.studyplan.dto.PlanoEstudoGetDto;
import com.satc.integrador.ai.user.UsuarioService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
class ExerciciosGpt {
    public TipoExercicios tipo;
    public Object dados;
}

interface IExerciciosGpt { }

@Data
@AllArgsConstructor
@NoArgsConstructor
class ExercicioDtoGramaticaCompl implements IExerciciosGpt {
    @JsonProperty("frase_completa")
    public String fraseCompleta;

    @JsonProperty("frase_incompleta")
    public String fraseIncompleta;

    @JsonProperty("opcao_correta")
    public String opcaoCorreta;

    @JsonProperty("opcao_incorreta")
    public List<String> opcaoIncorreta;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ExercicioDtoGramaticaOrdem implements IExerciciosGpt {
    @JsonProperty("frase_completa")
    public String fraseCompleta;

    @JsonProperty("ordem_correta")
    public List<String> ordemCorreta;

    @JsonProperty("ordem_aleatoria")
    public List<String> ordemAleatoria;
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class ExercicioDtoVocPares implements IExerciciosGpt{
    @JsonProperty("pares_esquerda")
    public List<String> paresEsquerda;

    @JsonProperty("pares_direita")
    public List<String> paresDireita;
}

@Service
public class PlanoEstudoService {
    @Autowired private UsuarioService usuarioService;
    @Autowired private PreferenciaService preferenciaService;
    @Autowired private PlanoEstudoRepo repo;
    @Autowired private ExercicioGramaticaComplementarRepo exerGramaComplRepo;
    @Autowired private ExercicioGramaticaOrdemRepo exerGramaOrdemRepo;
    @Autowired private ExercicioVocabualrioParesRepo exerVocParesRepo;

    final String commandStudyPlan = "";

    public PlanoEstudoGetDto generateNewPlan() {
        Integer userId = usuarioService.getCurrentUserid();
        PreferenciaGetDto dtoPreferencia = preferenciaService.getCurrent();
        List<ExerciciosGpt> exerciciosResponse = new ArrayList<ExerciciosGpt>();
        {
            Integer qtExercicios = (dtoPreferencia.tempoMinutos() / 5) / 3;
            List<ExerciciosCall> exer = new ArrayList<ExerciciosCall>();
            exer.add(new ExerciciosCall(qtExercicios, TipoExercicios.GRAMATICA_COMPLEMENTAR));
            exer.add(new ExerciciosCall(qtExercicios, TipoExercicios.GRAMATICA_ORDEM));
            exer.add(new ExerciciosCall(qtExercicios, TipoExercicios.VOCABULARIO_PARES));
            //String responseJson = OpenAiExample.gerarExercicios(dtoPreferencia, exer);
            String responseJson  = "[\n" +
                    "  {\n" +
                    "    \"tipo\": \"GRAMATICA_COMPLEMENTAR\",\n" +
                    "    \"dados\": {\n" +
                    "      \"frase_completa\": \"SpongeBob lives in a pineapple under the sea.\",\n" +
                    "      \"frase_incompleta\": \"SpongeBob lives in a ... under the sea.\",\n" +
                    "      \"opcao_correta\": \"pineapple\",\n" +
                    "      \"opcao_incorreta\": [\"castle\", \"ship\", \"stone\"]\n" +
                    "    }\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"tipo\": \"GRAMATICA_COMPLEMENTAR\",\n" +
                    "    \"dados\": {\n" +
                    "      \"frase_completa\": \"Patrick is SpongeBob's best friend.\",\n" +
                    "      \"frase_incompleta\": \"Patrick is SpongeBob's best ...\",\n" +
                    "      \"opcao_correta\": \"friend\",\n" +
                    "      \"opcao_incorreta\": [\"neighbor\", \"enemy\", \"teacher\"]\n" +
                    "    }\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"tipo\": \"GRAMATICA_COMPLEMENTAR\",\n" +
                    "    \"dados\": {\n" +
                    "      \"frase_completa\": \"Jellyfish float gracefully through the ocean.\",\n" +
                    "      \"frase_incompleta\": \"Jellyfish ... gracefully through the ocean.\",\n" +
                    "      \"opcao_correta\": \"float\",\n" +
                    "      \"opcao_incorreta\": [\"run\", \"jump\", \"walk\"]\n" +
                    "    }\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"tipo\": \"GRAMATICA_ORDEM\",\n" +
                    "    \"dados\": {\n" +
                    "      \"frase_completa\": \"Sandy wears a suit to live underwater.\",\n" +
                    "      \"ordem_correta\": [\"Sandy\", \"wears\", \"a\", \"suit\", \"to\", \"live\", \"underwater.\"],\n" +
                    "      \"ordem_aleatoria\": [\"underwater.\", \"Sandy\", \"suit\", \"a\", \"live\", \"wears\", \"to\"]\n" +
                    "    }\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"tipo\": \"GRAMATICA_ORDEM\",\n" +
                    "    \"dados\": {\n" +
                    "      \"frase_completa\": \"The Krusty Krab is a famous burger restaurant.\",\n" +
                    "      \"ordem_correta\": [\"The\", \"Krusty\", \"Krab\", \"is\", \"a\", \"famous\", \"burger\", \"restaurant.\"],\n" +
                    "      \"ordem_aleatoria\": [\"famous\", \"burger\", \"restaurant.\", \"Krusty\", \"is\", \"a\", \"Krab\", \"The\"]\n" +
                    "    }\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"tipo\": \"GRAMATICA_ORDEM\",\n" +
                    "    \"dados\": {\n" +
                    "      \"frase_completa\": \"Squidward plays the clarinet every afternoon.\",\n" +
                    "      \"ordem_correta\": [\"Squidward\", \"plays\", \"the\", \"clarinet\", \"every\", \"afternoon.\"],\n" +
                    "      \"ordem_aleatoria\": [\"the\", \"afternoon.\", \"Squidward\", \"clarinet\", \"plays\", \"every\"]\n" +
                    "    }\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"tipo\": \"VOCABULARIO_PARES\",\n" +
                    "    \"dados\": {\n" +
                    "      \"pares_esquerda\": [\"sponge\", \"friend\", \"ocean\", \"pineapple\"],\n" +
                    "      \"pares_direita\": [\"esponja\", \"amigo\", \"oceano\", \"abacaxi\"]\n" +
                    "    }\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"tipo\": \"VOCABULARIO_PARES\",\n" +
                    "    \"dados\": {\n" +
                    "      \"pares_esquerda\": [\"crab\", \"clarinet\", \"jellyfish\", \"restaurant\"],\n" +
                    "      \"pares_direita\": [\"caranguejo\", \"clarinete\", \"água-viva\", \"restaurante\"]\n" +
                    "    }\n" +
                    "  },\n" +
                    "  {\n" +
                    "    \"tipo\": \"VOCABULARIO_PARES\",\n" +
                    "    \"dados\": {\n" +
                    "      \"pares_esquerda\": [\"underwater\", \"float\", \"suit\", \"sea\"],\n" +
                    "      \"pares_direita\": [\"debaixo d'água\", \"flutuar\", \"traje\", \"mar\"]\n" +
                    "    }\n" +
                    "  }\n" +
                    "]\n";
            ObjectMapper mapper = new ObjectMapper();
            try {
                exerciciosResponse = mapper.readValue(responseJson, new TypeReference<List<ExerciciosGpt>>() {});
            } catch (JsonProcessingException e) {
                System.out.println("Erro");
            }

        }
        PlanoEstudo planoEstudo = new PlanoEstudo(userId, dtoPreferencia.id(), dtoPreferencia.tempoMinutos() / 5, null);
        repo.save(planoEstudo);
        Integer i = 0;
        ObjectMapper mapper = new ObjectMapper();
        for(ExerciciosGpt exerRespose: exerciciosResponse){
            switch (exerRespose.tipo){
                case TipoExercicios.GRAMATICA_COMPLEMENTAR: {

                    ExercicioDtoGramaticaCompl x = mapper.convertValue(exerRespose.dados, ExercicioDtoGramaticaCompl.class);
                    ExercicioGramaticaComplementar exercicio = new ExercicioGramaticaComplementar();
                    exercicio.setIdOrdemExercicio(i);
                    exercicio.setIdPlanoEstudo(planoEstudo.getId());
                    exercicio.setFraseCompleta(x.fraseCompleta);
                    exercicio.setFraseIncompleta(x.fraseIncompleta);
                    exercicio.setOpcaoCorreta(x.opcaoCorreta);
                    exercicio.setOpcaoIncorreta(x.opcaoIncorreta);
                    exerGramaComplRepo.save(exercicio);
                } break;
                case TipoExercicios.GRAMATICA_ORDEM: {
                    ExercicioDtoGramaticaOrdem x = mapper.convertValue(exerRespose.dados, ExercicioDtoGramaticaOrdem.class);
                    ExercicioGramaticaOrdem exercicio = new ExercicioGramaticaOrdem();
                    exercicio.setIdOrdemExercicio(i);
                    exercicio.setIdPlanoEstudo(planoEstudo.getId());
                    exercicio.setFraseCompleta(x.fraseCompleta);
                    exercicio.setOrdemCorreta(x.ordemCorreta);
                    exercicio.setOrdemAleatoria(x.ordemAleatoria);
                    exerGramaOrdemRepo.save(exercicio);
                } break;
                case TipoExercicios.VOCABULARIO_PARES: {
                    ExercicioDtoVocPares x = mapper.convertValue(exerRespose.dados, ExercicioDtoVocPares.class);
                    ExercicioVocabualrioPares exercicio = new ExercicioVocabualrioPares();
                    exercicio.setIdOrdemExercicio(i);
                    exercicio.setIdPlanoEstudo(planoEstudo.getId());
                    exercicio.setParesEsquerda(x.paresEsquerda);
                    exercicio.setParesDireita(x.paresDireita);
                    exerVocParesRepo.save(exercicio);
                } break;
            }
            i++;
        }
        return PlanoEstudo.mapToDto(planoEstudo);
    }

    public PlanoEstudoGetDto getId(Integer id){
        Integer userId = usuarioService.getCurrentUserid();
        PlanoEstudo planoEstudo = repo.findByIdUsuario(id, userId);
        PlanoEstudoGetDto dto = PlanoEstudo.mapToDto(planoEstudo);
        {
            dto.exerGramaCompl = exerGramaComplRepo.findByPlanoEstudo(planoEstudo.getId())
                    .stream()
                    .map(ExercicioGramaticaComplementar::mapToDto)
                    .toList();

            dto.exerGramaOrdem = exerGramaOrdemRepo.findByPlanoEstudo(planoEstudo.getId())
                    .stream()
                    .map(ExercicioGramaticaOrdem::mapToDto)
                    .toList();;
            dto.exerVocPares =  exerVocParesRepo.findByPlanoEstudo(planoEstudo.getId())
                    .stream()
                    .map(ExercicioVocabualrioPares::mapToDto)
                    .toList();;
        }
        return dto;
    }

    public PlanoEstudoGetDto getCurrent() {
        Integer userId = usuarioService.getCurrentUserid();
        PlanoEstudo planoEstudo = repo.findByIdUsuarioActive(userId);
        PlanoEstudoGetDto dto = PlanoEstudo.mapToDto(planoEstudo);
        {
            dto.exerGramaCompl = exerGramaComplRepo.findByPlanoEstudo(planoEstudo.getId())
                    .stream()
                    .map(ExercicioGramaticaComplementar::mapToDto)
                    .toList();

            dto.exerGramaOrdem = exerGramaOrdemRepo.findByPlanoEstudo(planoEstudo.getId())
                    .stream()
                    .map(ExercicioGramaticaOrdem::mapToDto)
                    .toList();;
            dto.exerVocPares =  exerVocParesRepo.findByPlanoEstudo(planoEstudo.getId())
                    .stream()
                    .map(ExercicioVocabualrioPares::mapToDto)
                    .toList();;

        }
        return dto;
    }
}