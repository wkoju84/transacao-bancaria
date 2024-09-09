package com.bancaria.transacao.controllers;

import com.bancaria.transacao.dtos.EmpresaDTO;
import com.bancaria.transacao.dtos.TransacaoDTO;
import com.bancaria.transacao.services.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/transacoes")
public class TransacaoController {

    private TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @GetMapping
    public ResponseEntity<List<TransacaoDTO>> BuscarTodasAsTransacoes(){
        List<TransacaoDTO> dtoList = transacaoService.buscarTodos();
        return ResponseEntity.ok().body(dtoList);
    }
}
