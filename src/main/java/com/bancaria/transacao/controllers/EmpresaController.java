package com.bancaria.transacao.controllers;

import com.bancaria.transacao.dtos.EmpresaDTO;
import com.bancaria.transacao.services.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/empresas")
public class EmpresaController {

    private EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDTO>> BuscarTodasAsEmpresas(){
        List<EmpresaDTO> dtoList = empresaService.buscarTodos();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<EmpresaDTO> buscarEmpresaPorId(@PathVariable Long id){
        EmpresaDTO dto = empresaService.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirEmpresa(@PathVariable Long id){
        empresaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<EmpresaDTO> inserirEmpresa(@RequestBody EmpresaDTO dto){
        dto = empresaService.inserir(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
