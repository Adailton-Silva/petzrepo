package com.petz.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petz.cadastro.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
