package com.senai.personagem.service;

import java.time.LocalDate;
import java.util.List;
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
        // Regra de Negócio: não deve haver mais de um personagem com
        // o mesmo nome ! 
        boolean existeComNome = repository.existsByNome(personagem.getNome());
        if (existeComNome)
            throw new IllegalArgumentException("Nome já registrado!!!");
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

    public Personagem consultarPorNome(String nome) {
        Optional<Personagem> personagemOpt = repository.findByNome(nome);
        if (personagemOpt.isEmpty())
            throw new IllegalArgumentException("Personagem nao encontrado com o nome " + nome);
        return personagemOpt.get();
    }

    public Personagem consultarPor(String nome, String referencia) {
        Optional<Personagem> personagemOpt = repository.obterPorNomeEreferenciaComJPQL(nome, referencia);
        return personagemOpt.orElseThrow(() -> new IllegalArgumentException("Registro nao encontrado"));
    }
    
}
