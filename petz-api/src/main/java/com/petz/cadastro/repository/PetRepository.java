package com.petz.cadastro.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petz.cadastro.entities.Pet;

public interface PetRepository extends JpaRepository<Pet, Long>{

}
