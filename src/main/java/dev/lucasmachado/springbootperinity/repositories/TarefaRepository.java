package dev.lucasmachado.springbootperinity.repositories;

import dev.lucasmachado.springbootperinity.entities.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa,Long> {
}
