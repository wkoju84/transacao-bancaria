package com.bancaria.transacao.repositories;

import com.bancaria.transacao.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
