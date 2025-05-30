package com.satc.integrador.ai.studyPlan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.satc.integrador.ai.enums.TipoExercicios;
import com.satc.integrador.ai.models.ExercicioGramaticaComplementar;
import com.satc.integrador.ai.models.ExercicioGramaticaOrdem;
import com.satc.integrador.ai.models.ExercicioVocabualrioPares;
import com.satc.integrador.ai.models.PlanoEstudo;
import com.satc.integrador.ai.preference.PreferenciaService;
import com.satc.integrador.ai.preference.dto.PreferenciaGetDto;
import com.satc.integrador.ai.repository.ExercicioGramaticaComplementarRepo;
import com.satc.integrador.ai.repository.ExercicioGramaticaOrdemRepo;
import com.satc.integrador.ai.repository.ExercicioVocabualrioParesRepo;
import com.satc.integrador.ai.repository.PlanoEstudoRepo;
import com.satc.integrador.ai.studyPlan.dto.PlanoEstudoGetDto;
import com.satc.integrador.ai.user.UsuarioService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Data
class ExerciciosGpt {
    public TipoExercicios tipo;
    public Object dados;
}

class ExercicioDtoGramaticaCompl {
    public String fraseCompleta;
    public String fraseIncompleta;
    public String opcaoCorreta;
    public List<String> opcaoIncorreta;
}

class ExercicioDtoGramaticaOrdem {
    public String fraseCompleta;
    public List<String> ordemCorreta;
    public List<String> ordemAleatoria;
}

class ExercicioDtoVocPares {
    public List<String> paresEsquerda;
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
            String responseJson = OpenAiExample.gerarExercicios(dtoPreferencia, exer);
            ObjectMapper mapper = new ObjectMapper();
            try {
                exerciciosResponse = mapper.readValue(responseJson, new TypeReference<List<ExerciciosGpt>>() {});
            } catch (JsonProcessingException e) {

            }

        }
        PlanoEstudo planoEstudo = new PlanoEstudo(userId, dtoPreferencia.id(), dtoPreferencia.tempoMinutos() / 5, null);
        repo.save(planoEstudo);
        Integer i = 0;
        for(ExerciciosGpt exerRespose: exerciciosResponse){
            switch (exerRespose.tipo){
                case TipoExercicios.GRAMATICA_COMPLEMENTAR: {
                    ExercicioDtoGramaticaCompl x = (ExercicioDtoGramaticaCompl) exerRespose.dados;
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
                    ExercicioDtoGramaticaOrdem x = (ExercicioDtoGramaticaOrdem) exerRespose.dados;
                    ExercicioGramaticaOrdem exercicio = new ExercicioGramaticaOrdem();
                    exercicio.setIdOrdemExercicio(i);
                    exercicio.setIdPlanoEstudo(planoEstudo.getId());
                    exercicio.setFraseCompleta(x.fraseCompleta);
                    exercicio.setOrdemCorreta(x.ordemCorreta);
                    exercicio.setOrdemAleatoria(x.ordemAleatoria);
                    exerGramaOrdemRepo.save(exercicio);
                } break;
                case TipoExercicios.VOCABULARIO_PARES: {
                    ExercicioDtoVocPares x = (ExercicioDtoVocPares) exerRespose.dados;
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
}