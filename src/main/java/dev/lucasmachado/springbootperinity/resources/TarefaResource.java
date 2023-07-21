package dev.lucasmachado.springbootperinity.resources;

import dev.lucasmachado.springbootperinity.entities.Pessoa;
import dev.lucasmachado.springbootperinity.entities.Tarefa;
import dev.lucasmachado.springbootperinity.services.TarefaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/tarefas")
public class TarefaResource {

    @Autowired
    private TarefaService tarefaService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody Tarefa tarefa) {
        tarefa.setId(null);
        Tarefa tarefaSalva = tarefaService.save(tarefa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(tarefaSalva.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

   @RequestMapping(value = "/alocar/{id}",method = RequestMethod.PUT)
   public ResponseEntity<Tarefa> alocatePessoaInTarefa(@PathVariable Long id, @Valid @RequestBody Pessoa pessoa) {
        Tarefa tarefaParaAlocar = tarefaService.alocarPessoaNaTarefa(id,pessoa);
        return ResponseEntity.noContent().build();
   }

    @RequestMapping(value = "/finalizar/{id}",method = RequestMethod.PUT)
    public ResponseEntity<Tarefa> setTarefaFinalizada(@PathVariable Long id) {
        Tarefa tarefaParaFinalizar = tarefaService.setTarefaFinalizada(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/pendentes", method = RequestMethod.GET)
    public ResponseEntity<List<Tarefa>> listThreeTaskWithoutPessoaByPrazo(){
        return ResponseEntity.ok().body(tarefaService.listThreeTaskWithoutPessoaByPrazo());
    }

}
