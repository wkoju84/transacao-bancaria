package com.bancaria.transacao.repositories;

import com.bancaria.transacao.entities.Cliente;
import com.bancaria.transacao.tests.Factory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
public class ClienteRepositoryTests {

    @Autowired
    ClienteRepository clienteRepository;

    private long idExistente;
    private long idInexistente;

    private long countTotalClientes;

    private List<Cliente> list;

    @BeforeEach
    void setup() throws Exception{

        idExistente = 1;
        idInexistente = 99;
        countTotalClientes = 3;
        list = new ArrayList<>();
    }

    @Test
    public void saveDeveriaSalvarComAutoincrementoQuandoOIdForNulo(){
        Cliente cliente = Factory.criarCliente();//Simula um DTO
        cliente.setId(null);
        cliente = clienteRepository.save(cliente);

        Assertions.assertNotNull(cliente.getId());
        Assertions.assertEquals(countTotalClientes + 1, cliente.getId());
    }

    @Test
    public void findAllDeveriaRetornarUmaLista(){
        list = clienteRepository.findAll();
        Assertions.assertNotNull(list);
    }

    @Test
    public void buscarPorIdERetornarUmDentista(){
        Optional<Cliente> resultado = clienteRepository.findById(idExistente);
        Assertions.assertTrue(resultado.isPresent());
    }

    @Test
    public void buscarPorIdERetornarUmOptionalVazio(){
        Optional<Cliente> resultado = clienteRepository.findById(idInexistente);
        Assertions.assertTrue(resultado.isEmpty());
    }

    @Test
    public void deleteDeveriaExcluirUmRegistroDoBD(){
        clienteRepository.deleteById(idExistente);
        Optional<Cliente> resultado = clienteRepository.findById(idExistente);
        Assertions.assertTrue(resultado.isEmpty());
    }

    @Test
    public void deleteDeveriaNaoEncontrarORegistroNoBD(){
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            clienteRepository.deleteById(idInexistente);
        });
    }
}
