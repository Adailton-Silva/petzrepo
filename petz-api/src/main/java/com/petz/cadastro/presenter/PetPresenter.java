package com.petz.cadastro.presenter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petz.cadastro.entities.Pet;
import com.petz.cadastro.repository.ClienteRepository;
import com.petz.cadastro.repository.PetRepository;

@RestController
@RequestMapping({"/pets"})
public class PetPresenter{
	
private PetRepository repository;

@Autowired
private ClienteRepository clienteRepository;
	
	PetPresenter(PetRepository petRepository) {
		this.repository = petRepository;
	}
	
	@GetMapping
	public List<Pet> findAll(){
	   return repository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Pet> findById(@PathVariable Long id){
	   return repository.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Pet create(@RequestBody Pet pet){
	   return repository.save(pet);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id,
	                             @RequestBody Pet pet) {
	   return repository.findById(id)
	           .map(record -> {
	               record.setNome_pet(pet.getNome_pet());
	               record.setIdade(pet.getIdade());
	               record.setCliente(pet.getCliente());
	               
	               Pet updated = repository.save(record);
	               return ResponseEntity.ok().body(updated);
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(path ={"/{id}"})
	public ResponseEntity <?> delete(@PathVariable long id) {
	   return repository.findById(id)
	           .map(record -> {
	               repository.deleteById(id);
	               return ResponseEntity.ok().build();
	           }).orElse(ResponseEntity.notFound().build());
	}
	
	

}


