package dev.lucasmachado.springbootperinity.resources;

import dev.lucasmachado.springbootperinity.dto.DepartamentoDTO;
import dev.lucasmachado.springbootperinity.dto.PessoaDTO;
import dev.lucasmachado.springbootperinity.services.DepartamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentoResource {

    @Autowired
    private DepartamentoService departamentoService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<DepartamentoDTO>> listarPessoasComDepartamentoETotalHorasGastas() {
        return ResponseEntity.ok().body(departamentoService.findAll());
    }

}
