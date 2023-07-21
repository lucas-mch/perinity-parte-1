package dev.lucasmachado.springbootperinity.services;

import dev.lucasmachado.springbootperinity.enterprise.DefaultTest;
import dev.lucasmachado.springbootperinity.enterprise.exceptions.DataIntegrityException;
import dev.lucasmachado.springbootperinity.entities.Departamento;
import dev.lucasmachado.springbootperinity.entities.Pessoa;
import dev.lucasmachado.springbootperinity.entities.Tarefa;
import dev.lucasmachado.springbootperinity.repositories.PessoaRepository;
import dev.lucasmachado.springbootperinity.repositories.TarefaRepository;
import org.hibernate.ObjectNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@DefaultTest
public class TarefaServiceTest {
    @Mock
    private TarefaRepository tarefaRepository;

    @Mock
    private PessoaRepository pessoaRepository;

    @InjectMocks
    private TarefaService tarefaService;

    private Tarefa tarefa1;
    private Tarefa tarefa2;
    private Pessoa pessoa1;
    private Departamento departamento1;

    @BeforeEach
    public void setUp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        departamento1 = new Departamento(1L,"Desenvolvimento");
        pessoa1 = new Pessoa(1L,"Lucas Machado", departamento1);
        tarefa1 = new Tarefa(1L,"Testes unitarios","Implementar testes", LocalDate.parse("11-10-2023",formatter),departamento1,pessoa1,15,false);
        tarefa2 = new Tarefa(2L,"Testes unitarios","Implementar testes", LocalDate.parse("20-10-2023",formatter),departamento1,pessoa1,10,false);
    }

    @Test
    public void testFindByIdExistingId() {
        Long existingId = 1L;
        when(tarefaRepository.findById(existingId)).thenReturn(Optional.of(tarefa1));

        Tarefa result = tarefaService.findById(existingId);

        assertEquals(tarefa1, result);
    }

    @Test
    public void testFindByIdNonExistingId() {
        Long nonExistingId = 100L;
        when(tarefaRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> tarefaService.findById(nonExistingId));
    }

    @Test
    public void testSave() {
        Tarefa tarefaToSave = new Tarefa(/* Set up tarefaToSave */);

        when(tarefaRepository.save(tarefaToSave)).thenReturn(tarefa1);

        Tarefa result = tarefaService.save(tarefaToSave);

        assertEquals(tarefa1, result);
    }

    @Test
    public void testAlocarPessoaNaTarefaAlreadyAllocated() {
        Long tarefaId = 1L;
        when(tarefaRepository.findById(tarefaId)).thenReturn(Optional.of(tarefa1));
        when(pessoaRepository.findById(any())).thenReturn(Optional.of(pessoa1));
        tarefa1.setPessoa(pessoa1);

        assertThrows(DataIntegrityException.class, () -> tarefaService.alocarPessoaNaTarefa(tarefaId, pessoa1));
    }

    @Test
    public void testSetTarefaFinalizadaAlreadyFinished() {
        Long tarefaId = 1L;
        when(tarefaRepository.findById(tarefaId)).thenReturn(Optional.of(tarefa1));
        tarefa1.setHasFinalizado(true);

        assertThrows(DataIntegrityException.class, () -> tarefaService.setTarefaFinalizada(tarefaId));
    }

    @Test
    public void testListThreeTaskWithoutPessoaByPrazo() {
        List<Tarefa> mockTarefas = new ArrayList<>();
        mockTarefas.add(tarefa1);
        mockTarefas.add(tarefa2);

        when(tarefaRepository.listThreeTaskWithoutPessoaByPrazo()).thenReturn(mockTarefas);

        List<Tarefa> result = tarefaService.listThreeTaskWithoutPessoaByPrazo();

        assertEquals(2, result.size()); // Assuming two tarefas are returned
    }


}

