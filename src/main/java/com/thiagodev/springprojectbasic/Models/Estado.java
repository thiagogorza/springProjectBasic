package com.thiagodev.springprojectbasic.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String nome;

    @OneToMany(mappedBy="estado", orphanRemoval = true)
    @JsonBackReference
    List<Cidade> cidadeList = new ArrayList<>();


    public Estado(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }


}
