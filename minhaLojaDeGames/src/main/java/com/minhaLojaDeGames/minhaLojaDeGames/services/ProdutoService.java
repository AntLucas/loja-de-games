package com.minhaLojaDeGames.minhaLojaDeGames.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minhaLojaDeGames.minhaLojaDeGames.models.Produto;
import com.minhaLojaDeGames.minhaLojaDeGames.repositories.ProdutoRepository;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository repositoryp;
	
	public Optional <Produto> atualizarProduto (Long idProduto ,Produto dadosProduto){
		Optional<Produto> produtoExistente = repositoryp.findById(idProduto);
		
		if(produtoExistente.isPresent()) {
			produtoExistente.get().setCategoria(dadosProduto.getCategoria());
			produtoExistente.get().setTitulo(dadosProduto.getTitulo());
			
			return Optional.ofNullable(repositoryp.save(produtoExistente.get()));
		}
		else {
			return Optional.empty();
		}
	}
	
}
