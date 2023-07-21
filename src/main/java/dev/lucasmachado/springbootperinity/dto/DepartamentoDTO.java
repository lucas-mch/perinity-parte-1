package dev.lucasmachado.springbootperinity.dto;

import dev.lucasmachado.springbootperinity.entities.Departamento;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class DepartamentoDTO implements Serializable {

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min= 5, max = 80, message = "O tamanho deve ser entre 5 a 80 caracteres")
    private String nome;

    public DepartamentoDTO() {
    }

    public DepartamentoDTO(Departamento departamento) {
        this.nome = departamento.getNome();
    }

    public String getNome() {
        return nome;
    }
}
