package dev.lucasmachado.springbootperinity.services;

import dev.lucasmachado.springbootperinity.entities.Pessoa;
import dev.lucasmachado.springbootperinity.repositories.PessoaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(Pessoa.class, "Categoria com o id : " + id + " nao encontrada.")
        );
    }

    public Pessoa save(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }
    public Pessoa update(Pessoa pessoa) {
        findById(pessoa.getId());
        return pessoaRepository.saveAndFlush(pessoa);
    }


}
