package com.satc.integrador.ai.models;

import com.satc.integrador.ai.enums.TipoExercicios;
import com.satc.integrador.ai.preference.dto.PreferenciaGetDto;
import com.satc.integrador.ai.preference.dto.PreferenciaPostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "plano_estudo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanoEstudo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idUsuario;
    private Integer idPreferencia; //preferencias que moldaram esse plano

    private Integer qtExerciciosDia;
    private List<TipoExercicios> tiposExerciciosContidos;

    //Mappers
    public static PreferenciaGetDto mapToDto(Preferencia obj) {
        return new PreferenciaGetDto(
                obj.getId(),
                obj.getIdUsuario(),
                obj.getIdioma(),
                obj.getTipoExercicio(),
                obj.getTemas(),
                obj.getDificuldade(),
                obj.getNivel(),
                obj.getDiaSemana(),
                obj.getTempoMinutos(),
                obj.getAtivo()
        );
    }

    public static Preferencia mapToObj(PreferenciaPostDto dto) {
        Preferencia obj = new Preferencia();
        obj.setIdUsuario(dto.idUsuario());
        obj.setIdioma(dto.idioma());
        obj.setTipoExercicio(dto.tipoExercicio());
        obj.setTemas(dto.temas());
        obj.setDificuldade(dto.dificuldade());
        obj.setNivel(dto.nivel());
        obj.setDiaSemana(dto.diaSemana());
        obj.setTempoMinutos(dto.tempoMinutos());
        obj.setAtivo(true);
        obj.setDataCriacao(new Date());
        return obj;
    }
}