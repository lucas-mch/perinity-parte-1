package dev.lucasmachado.springbootperinity.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.lucasmachado.springbootperinity.enterprise.AbstractEntity;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;


//- Cada Departamento terá id, nome.

@Entity
@Table(name = "departamentos")
public class Departamento extends AbstractEntity {
    private String nome;

    @OneToMany(mappedBy="departamento")
    @JsonIgnore
    private List<Pessoa> pessoas;

    @OneToMany(mappedBy="departamento")
    @JsonIgnore
    private List<Tarefa> tarefas;

    public Departamento() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
