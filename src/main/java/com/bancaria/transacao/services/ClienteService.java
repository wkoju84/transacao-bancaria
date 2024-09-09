package com.bancaria.transacao.services;

import com.bancaria.transacao.dtos.ClienteDTO;
import com.bancaria.transacao.dtos.EmpresaDTO;
import com.bancaria.transacao.entities.Cliente;
import com.bancaria.transacao.entities.Empresa;
import com.bancaria.transacao.repositories.ClienteRepository;
import com.bancaria.transacao.services.exceptions.EntidadeNaoEncontradaException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    public List<ClienteDTO> buscarTodos(){
        List<Cliente> list = clienteRepository.findAll();
        return list.stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ClienteDTO buscarPorId(Long id){
        Optional<Cliente> objeto = clienteRepository.findById(id);
        Cliente entidade = objeto.orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Registro " + id + " não encontrado em sua base de dados!"
        ));
        return new ClienteDTO(entidade);
    }
}
