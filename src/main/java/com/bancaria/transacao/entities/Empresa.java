package com.bancaria.transacao.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cnpj;
    private BigDecimal saldo = BigDecimal.ZERO;
    private BigDecimal taxaSistema;

    public Empresa() {
    }

    public Empresa(Long id, String nome, String cnpj, BigDecimal saldo, BigDecimal taxaSistema) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.saldo = saldo;
        this.taxaSistema = taxaSistema;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public BigDecimal getTaxaSistema() {
        return taxaSistema;
    }

    public void setTaxaSistema(BigDecimal taxaSistema) {
        this.taxaSistema = taxaSistema;
    }


}
