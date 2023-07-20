package dev.lucasmachado.springbootperinity.entities;

//- Cada pessoa terá um id, nome, departamento e  lista de tarefas

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.lucasmachado.springbootperinity.enterprise.AbstractEntity;
import javax.persistence.*;

import java.util.List;

@Entity
public class Pessoa extends AbstractEntity {

    private String nome;
    private Departamento departamento;

    @OneToMany(mappedBy="pessoa")
    @JsonIgnore
    private List<Tarefa> tarefas;
}
