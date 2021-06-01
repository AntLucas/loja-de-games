package com.minhaLojaDeGames.minhaLojaDeGames.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.minhaLojaDeGames.minhaLojaDeGames.models.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	public List<Categoria> findAllByDescricaoContainingIgnoreCase (String descricao);
	
	Optional<Categoria> findByDescricao (String descricao);
	
}
