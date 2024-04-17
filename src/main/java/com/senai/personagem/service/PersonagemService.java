package com.senai.personagem.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.senai.personagem.model.Personagem;
import com.senai.personagem.repository.PersonagemRepository;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository repository;


    public List<Personagem> consultar() {
        return repository.findAll();
    }

    public Personagem inserir(Personagem personagem) {
        personagem.setDataCadastro(LocalDate.now());
        personagem = repository.save(personagem);
        return personagem;
    }

    public Personagem consultarPorId(Integer id) {
        Optional<Personagem> personagemOpt = repository.findById(id);
        if (personagemOpt.isEmpty())
            throw new IllegalArgumentException("Personagem nao encontrado com o id " + id);
        Personagem personagem = personagemOpt.get();
        return personagem;
        // mesma coisa que o abaixo:
        // return repository.findById(id)
        //     .orElseThrow(() -> new IllegalArgumentException("Personagem nao encontrado com o id " + id)));
    }

    public void excluir(Integer id) {
        boolean existe = repository.existsById(id);
        if (!existe)
            throw new IllegalArgumentException("Personagem nao encontrado com o id " + id);
        repository.deleteById(id);
    }
    
}
