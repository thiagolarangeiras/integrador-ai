package com.satc.integrador.ai.models;

import com.satc.integrador.ai.enums.TipoExercicios;
import com.satc.integrador.ai.preference.dto.PreferenciaGetDto;
import com.satc.integrador.ai.preference.dto.PreferenciaPostDto;
import com.satc.integrador.ai.studyPlan.dto.PlanoEstudoGetDto;
import com.satc.integrador.ai.studyPlan.dto.PlanoEstudoPostDto;
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
    private Boolean ativo;

    public PlanoEstudo(Integer idUsuario, Integer idPreferencia, Integer qtExercicios, List<TipoExercicios> tiposExerciciosContidos){
        this.idUsuario = idUsuario;
        this.idPreferencia = idPreferencia;
        this.qtExerciciosDia = qtExerciciosDia;
        this.tiposExerciciosContidos = tiposExerciciosContidos;
    }

    //Mappers
    public static PlanoEstudoGetDto mapToDto(PlanoEstudo obj) {
        PlanoEstudoGetDto dto = new PlanoEstudoGetDto();
        dto.id = obj.getId();
        dto.idUsuario = obj.getIdUsuario();
        dto.idPreferencia = obj.getIdPreferencia();
        dto.qtExerciciosDia = obj.getQtExerciciosDia();
        dto.tiposExerciciosContidos = obj.getTiposExerciciosContidos();
        return dto;
    }

    public static PlanoEstudo mapToObj(PlanoEstudoPostDto dto) {
        PlanoEstudo obj = new PlanoEstudo();
        obj.setIdUsuario(dto.idUsuario);
        obj.setIdPreferencia(dto.idPreferencia);
        obj.setQtExerciciosDia(dto.qtExerciciosDia);
        obj.setTiposExerciciosContidos(dto.tiposExerciciosContidos);
        return obj;
    }
}