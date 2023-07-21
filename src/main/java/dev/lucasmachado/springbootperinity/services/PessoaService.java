package dev.lucasmachado.springbootperinity.services;

import dev.lucasmachado.springbootperinity.dto.PessoaDTO;
import dev.lucasmachado.springbootperinity.dto.PessoaGastosDTO;
import dev.lucasmachado.springbootperinity.enterprise.exceptions.DataIntegrityException;
import dev.lucasmachado.springbootperinity.entities.Pessoa;
import dev.lucasmachado.springbootperinity.entities.Tarefa;
import dev.lucasmachado.springbootperinity.repositories.PessoaRepository;
import dev.lucasmachado.springbootperinity.repositories.TarefaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private TarefaRepository tarefaRepository;

    public Pessoa findById(Long id) {
        return pessoaRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(Pessoa.class, "Pessoa com o id : " + id + " nao encontrada.")
        );
    }

    public Pessoa save(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }
    public Pessoa update(Pessoa newData) {
        Pessoa pessoaToUpdate = findById(newData.getId());
        if(pessoaToUpdate != null) {
            if(newData.getNome() != null) { pessoaToUpdate.setNome(newData.getNome());}
            if(newData.getDepartamento() != null) { pessoaToUpdate.setDepartamento(newData.getDepartamento());}
        }
        return pessoaRepository.saveAndFlush(pessoaToUpdate);
    }

    public void delete(Long id) {
        if (pessoaRepository.existsById(id)) {
            try {
                pessoaRepository.deleteById(id);
            } catch (DataIntegrityViolationException e) {
                throw new DataIntegrityException("Existem pessoas atrelados a xqdl id: " + id);
            }
        } else {
            throw new ObjectNotFoundException(Pessoa.class, "Pessoa com o id : " + id + " nao encontrado");
        }
    }

    public List<PessoaDTO> listarPessoasComDepartamentoETotalHorasGastas() {
        return pessoaRepository.listarPessoasComDepartamentoETotalHorasGastas().stream().map(PessoaDTO::new).collect(Collectors.toList());
    }

    public List<PessoaGastosDTO> findPessoasByNomeAndPeriod(String nome, String dataInicio, String dataFim) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        List<Pessoa> pessoas = pessoaRepository.findByNomeContainingIgnoreCase(nome);

        return pessoas.stream()
                .map(pessoa -> {
                    List<Tarefa> tarefas = tarefaRepository.findByPessoaAndPrazoBetween(pessoa, LocalDate.parse(dataInicio,formatter), LocalDate.parse(dataFim,formatter));

                    double averageHours = tarefas.stream()
                            .mapToDouble(Tarefa::getDuracao)
                            .average()
                            .orElse(0.0);

                    return new PessoaGastosDTO(pessoa.getId(),pessoa.getNome(), averageHours);
                })
                .collect(Collectors.toList());
    }


}
