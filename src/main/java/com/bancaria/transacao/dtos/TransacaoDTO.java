package com.bancaria.transacao.dtos;

import com.bancaria.transacao.entities.Cliente;
import com.bancaria.transacao.entities.Empresa;
import com.bancaria.transacao.entities.Transacao;
import com.bancaria.transacao.enums.TipoTransacao;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransacaoDTO {

    private Long id;
    private Empresa empresa;
    private Cliente cliente;
    private BigDecimal valor;
    private TipoTransacao tipoTransacao;
    private BigDecimal valorTaxa;
    private LocalDateTime dataHora;

    public TransacaoDTO() {
    }

    public TransacaoDTO(Long id, Empresa empresa, Cliente cliente, BigDecimal valor,
                        TipoTransacao tipoTransacao, BigDecimal valorTaxa, LocalDateTime dataHora) {
        this.id = id;
        this.empresa = empresa;
        this.cliente = cliente;
        this.valor = valor;
        this.tipoTransacao = tipoTransacao;
        this.valorTaxa = valorTaxa;
        this.dataHora = dataHora;
    }

    public TransacaoDTO(Transacao transacao) {
        this.id = transacao.getId();
        this.empresa = transacao.getEmpresa();
        this.cliente = transacao.getCliente();
        this.valor = transacao.getValor();
        this.tipoTransacao = transacao.getTipoTransacao();
        this.valorTaxa = transacao.getValorTaxa();
        this.dataHora = transacao.getDataHora();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoTransacao getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(TipoTransacao tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }

    public BigDecimal getValorTaxa() {
        return valorTaxa;
    }

    public void setValorTaxa(BigDecimal valorTaxa) {
        this.valorTaxa = valorTaxa;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
