package dev.lucasmachado.springbootperinity.services;

import dev.lucasmachado.springbootperinity.dto.DepartamentoDTO;
import dev.lucasmachado.springbootperinity.enterprise.DefaultTest;
import dev.lucasmachado.springbootperinity.entities.Departamento;
import dev.lucasmachado.springbootperinity.repositories.DepartamentoRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
@DefaultTest
public class DepartamentoServiceTest {

    @Mock
    private DepartamentoRepository departamentoRepository;

    @InjectMocks
    private DepartamentoService departamentoService;

    @Test
    public void testFindAll() {
        Departamento departamento1 = new Departamento();
        Departamento departamento2 = new Departamento();

        List<Departamento> mockDepartamentos = Arrays.asList(departamento1, departamento2);

        when(departamentoRepository.findAll()).thenReturn(mockDepartamentos);

        List<DepartamentoDTO> result = departamentoService.findAll();

        assertEquals(2, result.size());
    }

    @Test
    public void testFindAllEmpty() {
        List<Departamento> mockDepartamentos = Collections.emptyList();

        when(departamentoRepository.findAll()).thenReturn(mockDepartamentos);

        List<DepartamentoDTO> result = departamentoService.findAll();
        assertTrue(result.isEmpty());
    }

}
