package dev.lucasmachado.springbootperinity.repositories;

import dev.lucasmachado.springbootperinity.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface PessoaRepository extends JpaRepository<Pessoa,Long>, QuerydslPredicateExecutor<Pessoa> {

    @Query("SELECT p, d, SUM(t.duracao) FROM Pessoa p LEFT JOIN p.tarefas t LEFT JOIN t.departamento d GROUP BY p.id, d.id")
    List<Pessoa> listarPessoasComDepartamentoETotalHorasGastas();
}
