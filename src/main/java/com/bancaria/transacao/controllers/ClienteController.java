package com.bancaria.transacao.controllers;

import com.bancaria.transacao.dtos.ClienteDTO;
import com.bancaria.transacao.dtos.EmpresaDTO;
import com.bancaria.transacao.services.ClienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> BuscarTodosOsClientes(){
        List<ClienteDTO> dtoList = clienteService.buscarTodos();
        return ResponseEntity.ok().body(dtoList);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> buscarClientePorId(@PathVariable Long id){
        ClienteDTO dto = clienteService.buscarPorId(id);
        return ResponseEntity.ok().body(dto);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id){
        clienteService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
