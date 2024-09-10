package com.bancaria.transacao.services;

import com.bancaria.transacao.dtos.TransacaoDTO;
import com.bancaria.transacao.entities.Empresa;
import com.bancaria.transacao.entities.Transacao;
import com.bancaria.transacao.enums.TipoTransacao;
import com.bancaria.transacao.repositories.ClienteRepository;
import com.bancaria.transacao.repositories.EmpresaRepository;
import com.bancaria.transacao.repositories.TransacaoRepository;
import com.bancaria.transacao.services.exceptions.BancoDeDadosException;
import com.bancaria.transacao.services.exceptions.EntidadeNaoEncontradaException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TransacaoService {

    private TransacaoRepository transacaoRepository;

    private EmpresaRepository empresaRepository;

    private ClienteRepository clienteRepository;

    public TransacaoService(TransacaoRepository transacaoRepository, EmpresaRepository empresaRepository) {
        this.transacaoRepository = transacaoRepository;
        this.empresaRepository = empresaRepository;
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

    @Transactional
    public TransacaoDTO realizarTransacao(TransacaoDTO dto){

        // Encontrar Empresa pelo ID ou lança uma exceção se não encontrar
        Empresa empresa = empresaRepository.findById(dto.getEmpresa().getId())
                .orElseThrow(() -> new EntidadeNaoEncontradaException(
                        "Empresa não encontrada"
                ));

        //calcula o valor da taxa
        BigDecimal valorTaxa = dto.getValor().multiply(empresa.getTaxaSistema());

        //verifica tipo de transação e aplica regras de negócio
        if (dto.getTipoTransacao() == TipoTransacao.SAQUE){
            BigDecimal valorTotal = dto.getValor().add(valorTaxa);

            if (empresa.getSaldo().compareTo(valorTotal) < 0){
                throw new IllegalArgumentException(
                        "Saldo insuficiente para realizar o saque."
                );
            }
            empresa.setSaldo(empresa.getSaldo().subtract(valorTotal));
        }else if (dto.getTipoTransacao() == TipoTransacao.DEPOSITO){
            empresa.setSaldo(empresa.getSaldo().add(dto.getValor().subtract(valorTaxa)));
        }

        // Atualiza o saldo da Empresa no banco de dados
        empresaRepository.save(empresa);

        //cria entidade de transação
        Transacao entidade = new Transacao();
        copiarDtoParaEntidade(dto, entidade);
        entidade.setValorTaxa(dto.getValorTaxa());
        entidade.setEmpresa(dto.getEmpresa());

        //salva a transação
        entidade = transacaoRepository.save(entidade);

        //realizar a notificação


        // Retornar DTO da transação realizada
        return new TransacaoDTO(entidade);
//        return null;
    }

    private void copiarDtoParaEntidade(TransacaoDTO dto, Transacao entidade){
        entidade.setEmpresa(dto.getEmpresa());
        entidade.setCliente(dto.getCliente());
        entidade.setValor(dto.getValor());
        entidade.setTipoTransacao(dto.getTipoTransacao());
        entidade.setValorTaxa(dto.getValorTaxa());
        entidade.setDataHora(dto.getDataHora()!= null ? dto.getDataHora() : LocalDateTime.now());
    }
}


