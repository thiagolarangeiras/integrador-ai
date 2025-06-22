package com.satc.integrador.ai.studyplan;

import com.satc.integrador.ai.enums.TipoExercicios;
import com.satc.integrador.ai.studyplan.dto.PlanoEstudoGetDto;
import com.satc.integrador.ai.studyplan.dto.PlanoEstudoPostDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "plano_estudo")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanoEstudo { // Alterar o nome para plano_diario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer idUsuario;
    private Integer idPreferencia; //preferencias que moldaram esse plano

    private String nome;
    private Integer qtExercicios;
    private Integer qtExerciciosFinalizados;
    private List<TipoExercicios> tiposExerciciosContidos;
    private LocalDate data;
    private Boolean ativo;
    private Boolean finalizado;

    public PlanoEstudo(
            Integer idUsuario,
            Integer idPreferencia,
            String nivel,
            String idioma,
            Integer qtExercicios,
            List<TipoExercicios> tiposExerciciosContidos
    ){
        this.idUsuario = idUsuario;
        this.idPreferencia = idPreferencia;
        this.nome = idioma + " " + nivel;
        this.qtExercicios = qtExercicios;
        this.qtExerciciosFinalizados = 0;
        this.tiposExerciciosContidos = tiposExerciciosContidos;
        this.data = LocalDate.now();
        this.ativo = true;
        this.finalizado = false;
    }

    //Mappers
    public static PlanoEstudoGetDto mapToDto(PlanoEstudo obj) {
        PlanoEstudoGetDto dto = new PlanoEstudoGetDto();
        dto.id = obj.getId();
        dto.idUsuario = obj.getIdUsuario();
        dto.idPreferencia = obj.getIdPreferencia();
        dto.nome = obj.nome;
        dto.data = obj.data;
        dto.qtExercicios = obj.getQtExercicios();
        dto.qtExerciciosFinalizados = obj.getQtExerciciosFinalizados();
        dto.tiposExerciciosContidos = obj.getTiposExerciciosContidos();
        dto.ativo = obj.ativo;
        dto.finalizado = obj.finalizado;
        return dto;
    }

    public static PlanoEstudo mapToObj(PlanoEstudoPostDto dto) {
        PlanoEstudo obj = new PlanoEstudo();
        obj.setIdUsuario(dto.idUsuario);
        obj.setIdPreferencia(dto.idPreferencia);
        obj.setQtExercicios(dto.qtExerciciosDia);
        obj.setTiposExerciciosContidos(dto.tiposExerciciosContidos);
        return obj;
    }
}