package dev.lucasmachado.springbootperinity.services;

import dev.lucasmachado.springbootperinity.dto.PessoaDTO;
import dev.lucasmachado.springbootperinity.dto.PessoaGastosDTO;
import dev.lucasmachado.springbootperinity.enterprise.DefaultTest;
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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DefaultTest
public class PessoaServiceTest {

    @Mock
    private PessoaRepository pessoaRepository;

    @Mock
    private TarefaRepository tarefaRepository;

    @InjectMocks
    private PessoaService pessoaService;

    private Pessoa pessoa1;
    private Pessoa pessoa2;

    @BeforeEach
    public void setUp() {
        pessoa1 = new Pessoa(2L, "Hyllana Cabral", null);
        pessoa2 = new Pessoa(1L, "Lucas Machado", null);
    }

    @Test
    public void testFindByIdExistingId() {
        Long existingId = 1L;
        when(pessoaRepository.findById(existingId)).thenReturn(Optional.of(pessoa1));

        Pessoa result = pessoaService.findById(existingId);

        assertEquals(pessoa1, result);
    }

    @Test
    public void testFindByIdNonExistingId() {
        Long nonExistingId = 100L;
        when(pessoaRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> pessoaService.findById(nonExistingId));
    }

    @Test
    public void testFindDTOByIdExistingId() {
        Long existingId = 1L;
        when(pessoaRepository.findById(existingId)).thenReturn(Optional.of(pessoa1));

        PessoaDTO result = pessoaService.findDTOById(existingId);

        assertNotNull(result);
    }

    @Test
    public void testFindDTOByIdNonExistingId() {
        Long nonExistingId = 100L;
        when(pessoaRepository.findById(nonExistingId)).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> pessoaService.findDTOById(nonExistingId));
    }

    @Test
    public void testSave() {

        Pessoa pessoaToSave = new Pessoa(1L,"Lucas Machado", null);

        when(pessoaRepository.save(pessoaToSave)).thenReturn(pessoa1);

        Pessoa result = pessoaService.save(pessoaToSave);

        assertEquals(pessoa1, result);
    }

    @Test
    public void testUpdateExistingData() {
        Pessoa newData = new Pessoa(null,"Maria Eduarda", null);
        newData.setId(1L);

        when(pessoaRepository.findById(newData.getId())).thenReturn(Optional.of(pessoa1));
        when(pessoaRepository.saveAndFlush(pessoa1)).thenReturn(pessoa1);

        Pessoa result = pessoaService.update(newData);

        assertEquals(pessoa1, result);
    }

    @Test
    public void testUpdateNonExistingData() {
        Pessoa newData = new Pessoa(5L,"Geovane", null);

        when(pessoaRepository.findById(newData.getId())).thenReturn(Optional.empty());

        assertThrows(ObjectNotFoundException.class, () -> pessoaService.update(newData));
    }

    @Test
    public void testDeleteExistingId() {
        Long existingId = 1L;
        when(pessoaRepository.existsById(existingId)).thenReturn(true);

        pessoaService.delete(existingId);

        verify(pessoaRepository, times(1)).deleteById(existingId);
    }

    @Test
    public void testDeleteNonExistingId() {
        Long nonExistingId = 100L;
        when(pessoaRepository.existsById(nonExistingId)).thenReturn(false);

        assertThrows(ObjectNotFoundException.class, () -> pessoaService.delete(nonExistingId));
    }

    @Test
    public void testListarPessoasComDepartamentoETotalHorasGastas() {
        List<Pessoa> mockPessoas = new ArrayList<>();
        mockPessoas.add(pessoa1);
        mockPessoas.add(pessoa2);

        when(pessoaRepository.listarPessoasComDepartamentoETotalHorasGastas()).thenReturn(mockPessoas);

        List<PessoaDTO> result = pessoaService.listarPessoasComDepartamentoETotalHorasGastas();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindPessoasByNomeAndPeriod() {
        String nome = "Lucas";
        String dataInicio = "11-10-2023";
        String dataFim = "21-10-2023";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        List<Tarefa> mockTarefas1 = new ArrayList<>();
        mockTarefas1.add(new Tarefa(1L,"Testes unitarios","Implementar testes", LocalDate.parse("11-10-2023",formatter),null,null,15,false));
        mockTarefas1.add(new Tarefa(2L,"Testes unitarios","Implementar testes", LocalDate.parse("20-10-2023",formatter),null,null,10,false));

        List<Tarefa> mockTarefas2 = new ArrayList<>();
        mockTarefas2.add(new Tarefa(3L,"Testes unitarios","Implementar testes", LocalDate.parse("12-10-2023",formatter),null,null,10,false));

        when(pessoaRepository.findByNomeContainingIgnoreCase(nome)).thenReturn(Arrays.asList(pessoa1, pessoa2));
        when(tarefaRepository.findByPessoaAndPrazoBetween(eq(pessoa1), any(), any())).thenReturn(mockTarefas1);
        when(tarefaRepository.findByPessoaAndPrazoBetween(eq(pessoa2), any(), any())).thenReturn(mockTarefas2);

        List<PessoaGastosDTO> result = pessoaService.findPessoasByNomeAndPeriod(nome, dataInicio, dataFim);

        assertEquals(2, result.size());
    }

}
