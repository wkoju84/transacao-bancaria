package com.bancaria.transacao.tests;

import com.bancaria.transacao.dtos.ClienteDTO;
import com.bancaria.transacao.dtos.EmpresaDTO;
import com.bancaria.transacao.dtos.TransacaoDTO;
import com.bancaria.transacao.entities.Cliente;
import com.bancaria.transacao.entities.Empresa;
import com.bancaria.transacao.entities.Transacao;

import java.time.LocalDateTime;

public class Factory {

    public static Cliente criarCliente(){
        return new Cliente(null, "Sofia Santos", "0123458769");
    }

    public static ClienteDTO criarClienteDTO(){
        Cliente cliente = criarCliente();
        return new ClienteDTO(cliente);
    }

    public static Empresa criarEmpresa(){
        return new Empresa(null, "Exxama", "01478520369/0001", null, null);
    }

    public static EmpresaDTO criarEmpresaDTO(){
        Empresa empresa = criarEmpresa();
        return new EmpresaDTO(empresa);
    }

//    public static Transacao criarTransacao(){
//        return new Transacao(null, "oxxo", "Manoel Sá", null, 0, null, LocalDateTime.now());
//    }

//    public static TransacaoDTO criarTransacaoDTO(){
//        Transacao transacao = criarTransacao();
//        return new TransacaoDTO(transacao);
//    }
}
