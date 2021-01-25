package com.petz.cadastro.presenter;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.petz.cadastro.entities.Cliente;
import com.petz.cadastro.repository.ClienteRepository;

@RestController
@RequestMapping({"/clientes"})
public class ClientePresenter {
	
	private ClienteRepository repository;
	
	ClientePresenter(ClienteRepository clienteRepository) {
		this.repository = clienteRepository;
	}
	
	@GetMapping
	public List<Cliente> findAll(){
	   return repository.findAll();
	}
	
	@GetMapping(path = {"/{id}"})
	public ResponseEntity<Cliente> findById(@PathVariable Long id){
	   return repository.findById(id)
	           .map(record -> ResponseEntity.ok().body(record))
	           .orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public Cliente create(@RequestBody Cliente cliente){
	   return repository.save(cliente);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Long id,
	                             @RequestBody Cliente cliente) {
	   return repository.findById(id)
	           .map(record -> {
	               record.setNome(cliente.getNome());
	               record.setEndereco(cliente.getEndereco());
	               record.setBairro(cliente.getBairro());
	               record.setCidade(cliente.getCidade());
	               record.setCpf(cliente.getCpf());
	               record.setTelefone(cliente.getTelefone());
	               Cliente updated = repository.save(record);
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
