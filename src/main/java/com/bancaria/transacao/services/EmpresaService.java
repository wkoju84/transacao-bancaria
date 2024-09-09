package com.bancaria.transacao.services;

import com.bancaria.transacao.dtos.EmpresaDTO;
import com.bancaria.transacao.entities.Empresa;
import com.bancaria.transacao.repositories.EmpresaRepository;
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
public class EmpresaService {

    private EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    @Transactional(readOnly = true)
    public List<EmpresaDTO> buscarTodos(){
        List<Empresa> list = empresaRepository.findAll();
        return list.stream().map(EmpresaDTO::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public EmpresaDTO buscarPorId(Long id){
        Optional<Empresa> objeto = empresaRepository.findById(id);
        Empresa entidade = objeto.orElseThrow(() -> new EntidadeNaoEncontradaException(
                "Registro " + id + " não encontrado em sua base de dados!"
        ));
        return new EmpresaDTO(entidade);
    }

    public void excluir(Long id){
        try {
            empresaRepository.deleteById(id);
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
    public EmpresaDTO inserir(EmpresaDTO dto){
        Empresa entidade = new Empresa();
        copiarDtoParaEntidade(dto, entidade);
        entidade = empresaRepository.save(entidade);
        return new EmpresaDTO(entidade);
    }

    @Transactional
    public EmpresaDTO atualizar(Long id, EmpresaDTO dto){
        try {
            Empresa entidade = empresaRepository.getReferenceById(id);
            copiarDtoParaEntidade(dto, entidade);
            entidade = empresaRepository.save(entidade);
            return new EmpresaDTO(entidade);
        }
        catch (EntityNotFoundException e){
            throw new EntidadeNaoEncontradaException(
                    "Registro " + id + " não encontrado em sua base de dados!"
            );
        }
    }

    private void copiarDtoParaEntidade(EmpresaDTO dto, Empresa entidade){
        entidade.setNome(dto.getNome());
        entidade.setCnpj(dto.getCnpj());
        entidade.setSaldo(dto.getSaldo());
        entidade.setTaxaSistema(dto.getTaxaSistema());
    }
}
