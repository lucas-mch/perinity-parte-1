package dev.lucasmachado.springbootperinity.entities;

//- Cada tarefa terá id, título, descrição, prazo, departamento, duração, pessoa alocada e se já foi finalizado.

import com.fasterxml.jackson.annotation.JsonBackReference;
import dev.lucasmachado.springbootperinity.enterprise.AbstractEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
public class Tarefa extends AbstractEntity {
    private String titulo;
    private String descricao;
    private LocalDateTime prazo;
    @JsonBackReference
    @OneToOne
    @JoinColumn(name = "i_pedidos", referencedColumnName = "pedidos")
    @MapsId
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name="i_pessoas", referencedColumnName = "id")
    private Pessoa pessoa;

    private Integer duracao;
    @Column(name = "fl_finalizado")
    private final Boolean hasFinalizado = Boolean.FALSE;

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

    public LocalDateTime getPrazo() {
        return prazo;
    }

    public void setPrazo(LocalDateTime prazo) {
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
}
