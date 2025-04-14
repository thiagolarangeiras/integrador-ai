package com.satc.integrador.ai.Preferencia;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.util.List;

@Entity
@Table(name = "preferencias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Preferencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idUsuario;

    private String idioma;
    private List<String> tipoExercicio;
    private List<String> temas;
    private String dificuldade;
    private String nivel;
    private List<DayOfWeek> diaSemana;
    private Integer tempoMinutos;

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
                obj.getTempoMinutos()
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
        return obj;
    }
}