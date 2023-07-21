package dev.lucasmachado.springbootperinity.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.lucasmachado.springbootperinity.enterprise.AbstractEntity;
import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="pessoas")
public class Pessoa extends AbstractEntity {

    private String nome;
    @ManyToOne
    @JoinColumn(name="i_departamentos", referencedColumnName = "id")
    private Departamento departamento;

    @OneToMany(mappedBy="pessoa")
    @JsonIgnore
    private List<Tarefa> tarefas;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<Tarefa> getTarefas() {
        return tarefas;
    }

}
