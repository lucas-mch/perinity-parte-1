package dev.lucasmachado.springbootperinity.services;

import dev.lucasmachado.springbootperinity.dto.DepartamentoDTO;
import dev.lucasmachado.springbootperinity.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

    public List<DepartamentoDTO> findAll(){
        return departamentoRepository.findAll().stream().map(DepartamentoDTO::new).collect(Collectors.toList());
    }

}
