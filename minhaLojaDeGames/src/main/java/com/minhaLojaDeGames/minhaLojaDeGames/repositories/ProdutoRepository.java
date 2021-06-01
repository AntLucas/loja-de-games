package com.minhaLojaDeGames.minhaLojaDeGames.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhaLojaDeGames.minhaLojaDeGames.models.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

	public Optional<Produto> findByTitulo (String titulo);
	 
}
