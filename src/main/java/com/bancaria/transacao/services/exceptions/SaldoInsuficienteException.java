package com.bancaria.transacao.services.exceptions;

import java.io.Serial;

public class SaldoInsuficienteException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public SaldoInsuficienteException(String msg){
        super(msg);
    }
}
