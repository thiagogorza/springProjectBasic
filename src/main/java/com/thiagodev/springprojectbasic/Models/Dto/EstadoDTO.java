package com.thiagodev.springprojectbasic.Models.Dto;
import com.thiagodev.springprojectbasic.Models.Estado;
import lombok.Getter;
import lombok.Setter;;
@Getter
@Setter
public class EstadoDTO {

    public EstadoDTO(Estado obj) {
        id = obj.getId();
        nome = obj.getNome();
    }

    private Integer id;
    private String nome;
}
