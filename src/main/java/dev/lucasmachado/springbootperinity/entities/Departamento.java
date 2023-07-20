package dev.lucasmachado.springbootperinity.entities;


import dev.lucasmachado.springbootperinity.enterprise.AbstractEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;


//- Cada Departamento terá id, nome.

@Entity
@Table(name = "departamentos")
public class Departamento extends AbstractEntity {
    private String nome;

    public Departamento() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }


}
