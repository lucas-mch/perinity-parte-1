package dev.lucasmachado.springbootperinity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.lucasmachado.springbootperinity.entities.Departamento;
import dev.lucasmachado.springbootperinity.entities.Pessoa;
import dev.lucasmachado.springbootperinity.entities.Tarefa;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class DepartamentoDTO implements Serializable {

    private Long id;
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min= 5, max = 80, message = "O tamanho deve ser entre 5 a 80 caracteres")
    private String nome;
    @JsonIgnore
    private List<Pessoa> pessoas;
    @JsonIgnore
    private List<Tarefa> tarefas;
    private Integer quantidadePessoas;
    private Integer quantidadeTarefas;

    public DepartamentoDTO() {
    }

    public DepartamentoDTO(Departamento departamento) {
        this.id = departamento.getId();
        this.nome = departamento.getNome();
        this.pessoas = departamento.getPessoas();
        this.tarefas = departamento.getTarefas();
    }

    public String getNome() {
        return nome;
    }
    public Integer getQuantidadePessoas() { return pessoas.size(); }
    public Integer getQuantidadeTarefas() { return tarefas.size(); }

    public Long getId() {
        return id;
    }
}
