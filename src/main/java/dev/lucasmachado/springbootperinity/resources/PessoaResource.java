package dev.lucasmachado.springbootperinity.resources;

import dev.lucasmachado.springbootperinity.entities.Pessoa;
import dev.lucasmachado.springbootperinity.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    //Adicionar um pessoa (post/pessoas)
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody Pessoa pessoa) {
        pessoa.setId(null);
        Pessoa pessoaSalva = pessoaService.save(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pessoaSalva.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //Alterar um pessoa (put/pessoas/{id})
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @Valid @RequestBody Pessoa pessoa) {
        pessoa.setId(id);
        pessoaService.update(pessoa);
        return ResponseEntity.noContent().build();
    }


}
