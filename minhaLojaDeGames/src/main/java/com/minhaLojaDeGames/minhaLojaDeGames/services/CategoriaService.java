package com.minhaLojaDeGames.minhaLojaDeGames.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhaLojaDeGames.minhaLojaDeGames.models.Categoria;
import com.minhaLojaDeGames.minhaLojaDeGames.models.Produto;
import com.minhaLojaDeGames.minhaLojaDeGames.repositories.CategoriaRepository;
import com.minhaLojaDeGames.minhaLojaDeGames.repositories.ProdutoRepository;



@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;
	@Autowired
	private ProdutoRepository repositoryp;
	
	/**
	 * Método para cadastrar categorias caso não haja alguma com a mesma descrição, caso haja não é cadastrado
	 * @param novaCategoria - objeto passado pelo controller
	 * @return um Optional com os dados cadastrados da nova descrição ou retorna um Optional vazio
	 * @author Antonio
	 */
	public Optional<Categoria> cadastrarPostagem (Categoria novaCategoria){
		Optional<Categoria> categoriaExistente = repository.findByDescricao(novaCategoria.getDescricao());
		
		if(categoriaExistente.isEmpty()) {
			return Optional.ofNullable(repository.save(novaCategoria));
		}
		else {
			return Optional.empty();
		}
	}
	
	public Optional<Categoria> atualizarCategoria (Long id, Categoria dadosCategoria){
		Optional<Categoria> categoriaExistente = repository.findById(id);
		if(categoriaExistente.isPresent()) {
			categoriaExistente.get().setDescricao(dadosCategoria.getDescricao());
			return Optional.ofNullable(repository.save(categoriaExistente.get()));
		}
		else {
			return Optional.empty();
		}
	}
	
	public Optional <Produto> cadastrarProduto (Long idCategoria, Produto novoProduto){
		Optional<Categoria> categoriaExistente = repository.findById(idCategoria);
		
		if(categoriaExistente.isPresent()) {
			novoProduto.setCategoria(categoriaExistente.get());
			return Optional.ofNullable(repositoryp.save(novoProduto));
		}
		else {
			return Optional.empty();
		}
	}
}
