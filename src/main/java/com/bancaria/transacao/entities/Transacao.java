package com.bancaria.transacao.entities;

import com.bancaria.transacao.enums.TipoTransacao;
import jakarta.persistence.*;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transacao implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Empresa empresa;

    @ManyToOne
    private Cliente cliente;

    private BigDecimal valor;

    private TipoTransacao tipoTransacao;

    private BigDecimal valorTaxa;

    private LocalDateTime dataHora;

    public Transacao() {
    }

    public Transacao(Long id, Empresa empresa, Cliente cliente, BigDecimal valor, TipoTransacao tipoTransacao,
                     BigDecimal valorTaxa, LocalDateTime dataHora) {
        this.id = id;
        this.empresa = empresa;
        this.cliente = cliente;
        this.valor = valor;
        this.tipoTransacao = tipoTransacao;
        this.valorTaxa = valorTaxa;
        this.dataHora = dataHora;
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
