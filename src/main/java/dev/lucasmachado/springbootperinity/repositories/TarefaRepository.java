package dev.lucasmachado.springbootperinity.repositories;

import dev.lucasmachado.springbootperinity.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> , QuerydslPredicateExecutor<Tarefa> {

    @Query("SELECT t FROM Tarefa t WHERE t.pessoa IS NULL ORDER BY t.prazo ASC")
    List<Tarefa> listThreeTaskWithoutPessoaByPrazo();

}
