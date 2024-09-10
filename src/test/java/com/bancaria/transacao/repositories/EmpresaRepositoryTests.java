package com.bancaria.transacao.repositories;

import com.bancaria.transacao.entities.Cliente;
import com.bancaria.transacao.entities.Empresa;
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
public class EmpresaRepositoryTests {

    @Autowired
    EmpresaRepository empresaRepository;

    private long idExistente;
    private long idInexistente;

    private long countTotalEmpresas;

    private List<Empresa> list;

    @BeforeEach
    void setup() throws Exception{

        idExistente = 1;
        idInexistente = 99;
        countTotalEmpresas = 3;
        list = new ArrayList<>();
    }

    @Test
    public void saveDeveriaSalvarComAutoincrementoQuandoOIdForNulo(){
        Empresa empresa = Factory.criarEmpresa();//Simula um DTO
        empresa.setId(null);
        empresa = empresaRepository.save(empresa);

        Assertions.assertNotNull(empresa.getId());
        Assertions.assertEquals(countTotalEmpresas + 1, empresa.getId());
    }

    @Test
    public void findAllDeveriaRetornarUmaLista(){
        list = empresaRepository.findAll();
        Assertions.assertNotNull(list);
    }

    @Test
    public void buscarPorIdERetornarUmDentista(){
        Optional<Empresa> resultado = empresaRepository.findById(idExistente);
        Assertions.assertTrue(resultado.isPresent());
    }

    @Test
    public void buscarPorIdERetornarUmOptionalVazio(){
        Optional<Empresa> resultado = empresaRepository.findById(idInexistente);
        Assertions.assertTrue(resultado.isEmpty());
    }

    @Test
    public void deleteDeveriaExcluirUmRegistroDoBD(){
        empresaRepository.deleteById(idExistente);
        Optional<Empresa> resultado = empresaRepository.findById(idExistente);
        Assertions.assertTrue(resultado.isEmpty());
    }

    @Test
    public void deleteDeveriaNaoEncontrarORegistroNoBD(){
        Assertions.assertThrows(EmptyResultDataAccessException.class, () -> {
            empresaRepository.deleteById(idInexistente);
        });
    }
}
