package com.senai.personagem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.senai.personagem.model.Personagem;

@Repository
public interface PersonagemRepository extends JpaRepository<Personagem, Integer> {

    Optional<Personagem> findByNome(String nome);

    boolean existsByNome(String nome);

    @Query(value = "SELECT * FROM personagens WHERE nome = :nome AND referencia = :referencia", 
        nativeQuery = true)
    Optional<Personagem> obterPorNomeEreferencia(String nome, String referencia);

    // usando JPQL
    @Query(value = "SELECT p FROM Personagem p WHERE p.nome = :nome AND p.referencia = :referencia")
    Optional<Personagem> obterPorNomeEreferenciaComJPQL(String nome, String referencia);
    
}
