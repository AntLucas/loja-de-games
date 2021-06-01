package com.minhaLojaDeGames.minhaLojaDeGames.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

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

import com.minhaLojaDeGames.minhaLojaDeGames.models.Produto;
import com.minhaLojaDeGames.minhaLojaDeGames.repositories.ProdutoRepository;
import com.minhaLojaDeGames.minhaLojaDeGames.services.CategoriaService;
import com.minhaLojaDeGames.minhaLojaDeGames.services.ProdutoService;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

	@Autowired
	private ProdutoRepository repository;
	
	@Autowired
	private CategoriaService servicec;
	
	@Autowired
	private ProdutoService servicep;
	
	
	@GetMapping("/todos")
	public ResponseEntity<List<Produto>> findAllProduto(){
		List<Produto> listaDeProdutos = repository.findAll();
		return ResponseEntity.status(200).body(listaDeProdutos);
	}
	
	@GetMapping("/id/{id}")
	public ResponseEntity<Produto> findByIDProduto (@PathVariable long id){
		return repository.findById(id)
				.map(produtoDoId -> ResponseEntity.status(200).body(produtoDoId))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@GetMapping("/titulo/{titulo}")
	public ResponseEntity<Produto> findByDescricaoTitulo (@PathVariable String titulo){
		
		return repository.findByTitulo(titulo)
				.map(retorno -> {
				return ResponseEntity.status(200).body(retorno);
				} )
				.orElse(ResponseEntity.status(404).build());
	}
	
	@PostMapping("/cadastrar/{idCategoria}")
	public ResponseEntity<Produto> postProduto (@PathVariable (value = "idCategoria") Long idCategoria,@Valid @RequestBody Produto novoProduto){
		return servicec.cadastrarProduto(idCategoria, novoProduto)
				.map(cadastrado -> ResponseEntity.status(201).body(cadastrado))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@PutMapping("/atualizar/{idProduto}")
	public ResponseEntity<Produto> putProduto (@PathVariable (value = "idProduto") Long idProduto,@Valid @RequestBody Produto dadosProduto){
		return servicep.atualizarProduto(idProduto, dadosProduto)
				.map(dadosAtualizados -> ResponseEntity.status(201).body(dadosAtualizados))
				.orElse(ResponseEntity.status(400).build());
	}
	
	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<Object> deleteProduto(@PathVariable long id){
		Optional<Produto> produtoExistente = repository.findById(id);
		
		if(produtoExistente.isPresent()) {
			repository.deleteById(id);
			return ResponseEntity.status(200).body("Produto deletado com sucesso!");
		} else {
			return ResponseEntity.status(400).body("O produto não existe.");
		}
	}
	
}
