package com.thiagodev.springprojectbasic.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany(mappedBy="estado", orphanRemoval = true)
    @JsonBackReference
    List<Cidade> cidadeList = new ArrayList<>();


    public Estado(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Estado estado = (Estado) o;
        return id != null && Objects.equals(id, estado.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
