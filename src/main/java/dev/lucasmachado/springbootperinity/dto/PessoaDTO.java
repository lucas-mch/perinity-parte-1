package dev.lucasmachado.springbootperinity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import dev.lucasmachado.springbootperinity.entities.Departamento;
import dev.lucasmachado.springbootperinity.entities.Pessoa;
import dev.lucasmachado.springbootperinity.entities.Tarefa;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class PessoaDTO implements Serializable {

    private Long id;

    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min = 5, max = 120, message = "O tamanho deve ser entre 5 a 120 caracteres")
    private String nome;
    private DepartamentoDTO departamento;
    @JsonIgnore
    private List<Tarefa> tarefas;
    private Integer horasGastasTarefas;

    public PessoaDTO(Pessoa pessoa) {
        this.id = pessoa.getId();
        this.nome = pessoa.getNome();
        this.departamento = new DepartamentoDTO(pessoa.getDepartamento());
        this.tarefas = pessoa.getTarefas();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getHorasGastasTarefas() {
        Integer count = 0;
        for (Tarefa tarefa : tarefas) {
            count += tarefa.getDuracao();
        }
        return count;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
