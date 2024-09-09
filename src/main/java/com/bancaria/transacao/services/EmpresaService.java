package com.bancaria.transacao.services;

import com.bancaria.transacao.dtos.EmpresaDTO;
import com.bancaria.transacao.entities.Empresa;
import com.bancaria.transacao.repositories.EmpresaRepository;
import com.bancaria.transacao.services.exceptions.EntidadeNaoEncontradaException;
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
}
