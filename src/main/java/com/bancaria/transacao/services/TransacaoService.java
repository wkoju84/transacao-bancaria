package com.bancaria.transacao.services;

import com.bancaria.transacao.dtos.EmpresaDTO;
import com.bancaria.transacao.dtos.TransacaoDTO;
import com.bancaria.transacao.entities.Empresa;
import com.bancaria.transacao.entities.Transacao;
import com.bancaria.transacao.repositories.TransacaoRepository;
import com.bancaria.transacao.services.exceptions.BancoDeDadosException;
import com.bancaria.transacao.services.exceptions.EntidadeNaoEncontradaException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    private TransacaoRepository transacaoRepository;

    public TransacaoService(TransacaoRepository transacaoRepository) {
        this.transacaoRepository = transacaoRepository;
    }

    @Transactional(readOnly = true)
    public List<TransacaoDTO> buscarTodos(){
        List<Transacao> list = transacaoRepository.findAll();
        return list.stream().map(TransacaoDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public TransacaoDTO buscarPorId(Long id){
        Optional<Transacao> objeto = transacaoRepository.findById(id);
        Transacao entidade = objeto.orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Registro " + id + " não encontrado em sua base de dados!"
        ));
        return new TransacaoDTO(entidade);
    }

    public void excluir(Long id){
        try {
            transacaoRepository.deleteById(id);
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
}
