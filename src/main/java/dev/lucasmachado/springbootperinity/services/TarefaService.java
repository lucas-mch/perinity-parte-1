package dev.lucasmachado.springbootperinity.services;

import com.querydsl.jpa.impl.JPAQueryFactory;
import dev.lucasmachado.springbootperinity.enterprise.exceptions.DataIntegrityException;
import dev.lucasmachado.springbootperinity.entities.Pessoa;
import dev.lucasmachado.springbootperinity.entities.Tarefa;
import dev.lucasmachado.springbootperinity.repositories.PessoaRepository;
import dev.lucasmachado.springbootperinity.repositories.TarefaRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tarefa findById(Long id) {
        return tarefaRepository.findById(id).orElseThrow(
                () -> new ObjectNotFoundException(Tarefa.class, "Tarefa com o id : " + id + " nao encontrada.")
        );
    }
    public Tarefa save(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public Tarefa alocarPessoaNaTarefa (Long id, Pessoa pessoaToAlocate) {
        Tarefa tarefaToAlocate = findById(id);
        Pessoa toAlocate = pessoaRepository.findById(pessoaToAlocate.getId()).orElse(null);

        if(!Objects.equals(toAlocate.getDepartamento().getId(), tarefaToAlocate.getDepartamento().getId())) {
            throw new DataIntegrityException("A pessoa informada não possuí o mesmo departamento da tarefa informada. Id: " + tarefaToAlocate.getDepartamento().getId());
        }

        if(Objects.equals(tarefaToAlocate.getPessoa().getId(), pessoaToAlocate.getId())) {
            throw new DataIntegrityException("A pessoa informada já está alocada na tarefa desejada.");
        }

        tarefaToAlocate.setPessoa(toAlocate);

        return this.save(tarefaToAlocate);
    }

    public Tarefa setTarefaFinalizada(Long id) {
        Tarefa tarefaToFinish = findById(id);
        if(tarefaToFinish.getHasFinalizado()) {
            throw new DataIntegrityException("A tarefa de id :" + id + " já possui o status de finalizada.");
        }
        tarefaToFinish.setHasFinalizado(true);
        return this.save(tarefaToFinish);
    }

    public List<Tarefa> listThreeTaskWithoutPessoaByPrazo() {
        return tarefaRepository.listThreeTaskWithoutPessoaByPrazo();
    }

}
