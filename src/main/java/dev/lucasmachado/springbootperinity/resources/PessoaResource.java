package dev.lucasmachado.springbootperinity.resources;

import dev.lucasmachado.springbootperinity.dto.PessoaDTO;
import dev.lucasmachado.springbootperinity.dto.PessoaGastosDTO;
import dev.lucasmachado.springbootperinity.entities.Pessoa;
import dev.lucasmachado.springbootperinity.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {

    @Autowired
    private PessoaService pessoaService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> save(@Valid @RequestBody Pessoa pessoa) {
        pessoa.setId(null);
        Pessoa pessoaSalva = pessoaService.save(pessoa);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(pessoaSalva.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Void> update(@PathVariable Long id,
                                       @Valid @RequestBody Pessoa pessoa) {
        pessoa.setId(id);
        pessoaService.update(pessoa);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        pessoaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<PessoaDTO>> listarPessoasComDepartamentoETotalHorasGastas() {
        return ResponseEntity.ok().body(pessoaService.listarPessoasComDepartamentoETotalHorasGastas());
    }

    @RequestMapping(value = "/gastos", method = RequestMethod.GET)
    public ResponseEntity<List<PessoaGastosDTO>> listarPessoasPorPeridoEMediaHorasGastas(
            @RequestParam String nome,
            @RequestParam(value = "dataInicio") String dataInicio,
            @RequestParam(value = "dataFim") String dataFim) {

        return ResponseEntity.ok().body(pessoaService.findPessoasByNomeAndPeriod(nome,dataInicio,dataFim));
    }

}
