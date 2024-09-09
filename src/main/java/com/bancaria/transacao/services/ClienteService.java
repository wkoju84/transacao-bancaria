package com.bancaria.transacao.services;

import com.bancaria.transacao.dtos.ClienteDTO;
import com.bancaria.transacao.dtos.EmpresaDTO;
import com.bancaria.transacao.entities.Cliente;
import com.bancaria.transacao.entities.Empresa;
import com.bancaria.transacao.repositories.ClienteRepository;
import com.bancaria.transacao.services.exceptions.BancoDeDadosException;
import com.bancaria.transacao.services.exceptions.EntidadeNaoEncontradaException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
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

    public void excluir(Long id){
        try {
            clienteRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e){
            throw new EntidadeNaoEncontradaException(
                    "Exclusão impossível: Registro " + id + " não encontrado em sua base de dados!"
            );
        }
        catch (DataIntegrityViolationException e){
            throw new BancoDeDadosException(
                    "Violação de integridade: Registro " + id + " está inserido em outro registro!"
            );
        }
    }

    @Transactional
    public ClienteDTO inserir(ClienteDTO dto){
        Cliente entidade = new Cliente();
        copiarDtoParaEntidade(dto, entidade);
        entidade = clienteRepository.save(entidade);
        return new ClienteDTO(entidade);
    }

    @Transactional
    public ClienteDTO atualizar(Long id, ClienteDTO dto){
        try {
            Cliente entidade = clienteRepository.getReferenceById(id);
            copiarDtoParaEntidade(dto, entidade);
            entidade = clienteRepository.save(entidade);
            return new ClienteDTO(entidade);
        }
        catch (EntityNotFoundException e){
            throw new EntidadeNaoEncontradaException(
                    "Registro " + id + " não encontrado em sua base de dados!"
            );
        }
    }

    private void copiarDtoParaEntidade(ClienteDTO dto, Cliente entidade){
        entidade.setNome(dto.getNome());
        entidade.setCpf(dto.getCpf());
    }
}
