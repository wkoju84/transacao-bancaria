package com.bancaria.transacao.repositories;

import com.bancaria.transacao.entities.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}
