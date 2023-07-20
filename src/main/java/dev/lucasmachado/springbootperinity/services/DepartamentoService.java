package dev.lucasmachado.springbootperinity.services;

import dev.lucasmachado.springbootperinity.repositories.DepartamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DepartamentoService {

    @Autowired
    private DepartamentoRepository departamentoRepository;

}
