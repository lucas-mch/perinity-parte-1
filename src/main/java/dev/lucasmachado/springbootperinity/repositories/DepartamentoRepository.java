package dev.lucasmachado.springbootperinity.repositories;

import dev.lucasmachado.springbootperinity.entities.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento,Long>, QuerydslPredicateExecutor<Departamento> {
}
