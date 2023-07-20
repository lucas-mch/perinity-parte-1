package dev.lucasmachado.springbootperinity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootPerinityApplication {

//    Funcionalidades desejadas:
//
//            - Adicionar um pessoa (post/pessoas)
//
// - Alterar um pessoa (put/pessoas/{id})
//
//            - Remover pessoa (delete/pessoas/{id})
//
//            - Adicionar um tarefa (post/tarefas)
//
// - Alocar uma pessoa na tarefa que tenha o mesmo departamento (put/tarefas/alocar/{id})
//
//            - Finalizar a tarefa (put/tarefas/finalizar/{id})
//
//            - Listar pessoas trazendo nome, departamento, total horas gastas nas tarefas.(get/pessoas)
//
//            - Buscar pessoas por nome e período, retorna média de horas gastas por tarefa. (get/pessoas/gastos)
//
//            - Listar 3 tarefas que estejam sem pessoa alocada com os prazos mais antigos. (get/tarefas/pendentes)
//
//            - Listar departamento e quantidade de pessoas e tarefas (get/departamentos)
//
//    OBS: A linguagem para realização do desafio deve ser em Java.
//
//    Framework SprintBoot ou Quarkus.
//
//    Bancos de dados: MongoDB, PostgreSQL.
    public static void main(String[] args) {
        SpringApplication.run(SpringBootPerinityApplication.class, args);
    }

}
