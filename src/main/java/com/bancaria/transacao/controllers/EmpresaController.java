package com.bancaria.transacao.controllers;

import com.bancaria.transacao.dtos.EmpresaDTO;
import com.bancaria.transacao.services.EmpresaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
