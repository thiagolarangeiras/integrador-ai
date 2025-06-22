package com.satc.integrador.ai.studyplan;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.satc.integrador.ai.chatgpt.GptService;
import com.satc.integrador.ai.chatgpt.dto.ExercicioDtoGramaticaCompl;
import com.satc.integrador.ai.chatgpt.dto.ExercicioDtoGramaticaOrdem;
import com.satc.integrador.ai.chatgpt.dto.ExercicioDtoVocPares;
import com.satc.integrador.ai.chatgpt.dto.ExerciciosGpt;
import com.satc.integrador.ai.enums.TipoExercicios;
import com.satc.integrador.ai.exercicios.*;
import com.satc.integrador.ai.preference.PreferenciaService;
import com.satc.integrador.ai.preference.dto.PreferenciaGetDto;
import com.satc.integrador.ai.studyplan.dto.PlanoEstudoGetDto;
import com.satc.integrador.ai.studyplan.dto.PlanoEstudoListaGetDto;
import com.satc.integrador.ai.user.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlanoEstudoService {
    @Autowired private PlanoEstudoRepo repo;
    @Autowired private ExercicioGramaticaComplementarRepo exerGramaComplRepo;
    @Autowired private ExercicioGramaticaOrdemRepo exerGramaOrdemRepo;
    @Autowired private ExercicioVocabualrioParesRepo exerVocParesRepo;

    @Autowired private UsuarioService usuarioService;
    @Autowired private PreferenciaService preferenciaService;
    @Autowired private GptService gptService;

    public void addExercicios(PlanoEstudoGetDto dto){
        dto.exerGramaCompl = exerGramaComplRepo.findByPlanoEstudo(dto.getId())
                .stream()
                .map(ExercicioGramaticaComplementar::mapToDto)
                .toList();

        dto.exerGramaOrdem = exerGramaOrdemRepo.findByPlanoEstudo(dto.getId())
                .stream()
                .map(ExercicioGramaticaOrdem::mapToDto)
                .toList();;
        dto.exerVocPares =  exerVocParesRepo.findByPlanoEstudo(dto.getId())
                .stream()
                .map(ExercicioVocabualrioPares::mapToDto)
                .toList();;
    }

    public PlanoEstudoListaGetDto getAll(Integer page, Integer count){
        Pageable pageable = PageRequest.of(page, count);
        var planos = new PlanoEstudoListaGetDto();
        planos.planos = repo.findByIdUsuario(usuarioService.getCurrentUserid(), pageable)
                .stream()
                .map(PlanoEstudo::mapToDto)
                .toList();
        Integer completos = 0;
        for(var plano : planos.planos){
            if(plano.finalizado){
                completos++;
            }
        }
        planos.porcentagemCompleta = (completos * 100) / planos.planos.size();
        return planos;
    }

    public PlanoEstudoGetDto getId(Integer id){
        Integer userId = usuarioService.getCurrentUserid();
        PlanoEstudo planoEstudo = repo.findByIdUsuario(id, userId);
        PlanoEstudoGetDto dto = PlanoEstudo.mapToDto(planoEstudo);
        addExercicios(dto);
        return dto;
    }

    public PlanoEstudoGetDto getCurrent() {
        Integer userId = usuarioService.getCurrentUserid();
        PlanoEstudo planoEstudo = repo.findByIdUsuarioActive(userId);
        if(planoEstudo == null){
            return generateNewPlan();
        }
        PlanoEstudoGetDto dto = PlanoEstudo.mapToDto(planoEstudo);
        addExercicios(dto);
        return dto;
    }

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
            String responseJson = gptService.gerarExercicios(dtoPreferencia, exer);
            ObjectMapper mapper = new ObjectMapper();
            try {
                exerciciosResponse = mapper.readValue(responseJson, new TypeReference<List<ExerciciosGpt>>() {});
            } catch (JsonProcessingException e) {
                System.out.println("Erro");
            }

        }
        PlanoEstudo planoEstudo = new PlanoEstudo(
                userId,
                dtoPreferencia.id(),
                dtoPreferencia.nivel(),
                dtoPreferencia.idioma(),
                dtoPreferencia.tempoMinutos() / 5,
                new ArrayList<TipoExercicios>(){{
                    add(TipoExercicios.VOCABULARIO_PARES);
                    add(TipoExercicios.GRAMATICA_ORDEM);
                    add(TipoExercicios.GRAMATICA_COMPLEMENTAR);
                }}
        );
        var planoAntigo = repo.findByIdUsuarioActive(userId);
        planoAntigo.setAtivo(false);
        repo.save(planoAntigo);
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

    public Boolean finalizarPlanoDiario(Integer id){
        Integer userId = usuarioService.getCurrentUserid();
        PlanoEstudo planoEstudo = repo.findByIdUsuario(id, userId);
        planoEstudo.setFinalizado(true);
        repo.save(planoEstudo);
        return true;
    }

    public Boolean finalizarExercicio(Integer id, Integer idExercicio){
        Integer userId = usuarioService.getCurrentUserid();
        PlanoEstudo planoEstudo = repo.findByIdUsuario(id, userId);

        // Essa coisa de separar uma tabela para eercicio ficou meio ruim, acho melhor salvar tudo em uma tabela
        // so e salvar os exercicios em um campo json numa tabela geral chamada exercicios, dai fica mais facil
        ExercicioGramaticaComplementar compl = exerGramaComplRepo.findByUsuario(idExercicio, id);
        if (compl != null){
            compl.setFinalizado(true);
            exerGramaComplRepo.save(compl);
        }
        ExercicioGramaticaOrdem ordem = exerGramaOrdemRepo.findByUsuario(idExercicio, id);
        if (ordem != null){
            ordem.setFinalizado(true);
            exerGramaOrdemRepo.save(ordem);
        }
        ExercicioVocabualrioPares vocPares = exerVocParesRepo.findByUsuario(idExercicio, id);
        if (vocPares != null){
            vocPares.setFinalizado(true);
            exerVocParesRepo.save(vocPares);
        }
        planoEstudo.setQtExerciciosFinalizados(planoEstudo.getQtExerciciosFinalizados() + 1);
        repo.save(planoEstudo);
        PlanoEstudoGetDto dto = PlanoEstudo.mapToDto(planoEstudo);
        addExercicios(dto);
        return true;
    }
}