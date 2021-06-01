package com.minhaLojaDeGames.minhaLojaDeGames.controllers;

import java.util.List;
import java.util.Optional;

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

import com.minhaLojaDeGames.minhaLojaDeGames.models.Categoria;
import com.minhaLojaDeGames.minhaLojaDeGames.repositories.CategoriaRepository;
import com.minhaLojaDeGames.minhaLojaDeGames.services.CategoriaService;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

	@Autowired
	private CategoriaRepository repository;
	@Autowired
	private CategoriaService service;
	
	
	@GetMapping("/todas")
	public ResponseEntity<List<Categoria>> findAllCategoria (){
		List<Categoria> listaDeCategorias = repository.findAll();
		return ResponseEntity.status(200).body(listaDeCategorias);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Categoria> findByIDCategoria (@PathVariable long id){
		return repository.findById(id)
				.map(categoriaDoId -> ResponseEntity.status(200).body(categoriaDoId))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@GetMapping("/descricao/{descricao}")
	public ResponseEntity<List<Categoria>> findByDescricaoCategoria (@PathVariable String descricao){
		
		return ResponseEntity.status(200).body(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<Categoria> postCategoria (@RequestBody Categoria novaCategoria){
		return service.cadastrarPostagem(novaCategoria)
				.map(categoriaCadastrada -> ResponseEntity.status(201).body(categoriaCadastrada))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Categoria> putCategoria (@PathVariable (value = "id") Long id ,@RequestBody Categoria dadosNovos){
		return service.atualizarCategoria(id, dadosNovos)
				.map(categoriaAtualizada -> ResponseEntity.status(201).body(categoriaAtualizada))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Categoria> deleteCategoria(@PathVariable long id) {
		Optional<Categoria> categoriaExistente = repository.findById(id);
		
		if(categoriaExistente.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).body(null);
		}
		else {
			return ResponseEntity.status(400).body(null);
		}
	}
	
}
