package dev.lucasmachado.springbootperinity.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import dev.lucasmachado.springbootperinity.enterprise.AbstractEntity;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

@Entity
@Table(name = "tarefas")
public class Tarefa extends AbstractEntity {
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min= 3, max = 80, message = "O tamanho deve ser entre 5 a 80 caracteres")
    private String titulo;
    @NotEmpty(message = "Preenchimento obrigatorio")
    @Length(min= 3, max = 200, message = "O tamanho deve ser entre 5 a 200 caracteres")
    private String descricao;
    @JsonFormat(pattern="dd-MM-yyyy")
    private LocalDate prazo;
    @ManyToOne
    @JoinColumn(name="i_departamentos", referencedColumnName = "id")
    @NotEmpty
    private Departamento departamento;
    @ManyToOne
    @JoinColumn(name="i_pessoas", referencedColumnName = "id")
    private Pessoa pessoa;
    private Integer duracao;
    @Column(name = "fl_finalizado")
    private Boolean hasFinalizado = Boolean.FALSE;

    public Tarefa() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDate prazo) {
        this.prazo = prazo;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public void setDuracao(Integer duracao) {
        this.duracao = duracao;
    }

    public Boolean getHasFinalizado() {
        return hasFinalizado;
    }

    public void setHasFinalizado(Boolean finalizado) { this.hasFinalizado = finalizado; }


}
