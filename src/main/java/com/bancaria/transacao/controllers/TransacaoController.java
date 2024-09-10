package com.bancaria.transacao.controllers;

import com.bancaria.transacao.dtos.TransacaoDTO;
import com.bancaria.transacao.services.TransacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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

    @GetMapping(value = "/{id}")
    public ResponseEntity<TransacaoDTO> buscarTransacaoPorId(@PathVariable Long id){
        TransacaoDTO dto = transacaoService.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirTransacao(@PathVariable Long id){
        transacaoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<TransacaoDTO> inserirTransacao(@RequestBody TransacaoDTO dto){

        // Chama o serviço para realizar a transação
        TransacaoDTO novaTransacao = transacaoService.realizarTransacao(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(novaTransacao.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
