package com.senai.personagem.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.senai.personagem.model.Personagem;
import com.senai.personagem.service.PersonagemService;

@RestController
@RequestMapping("personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService service;


    @GetMapping
    public ResponseEntity<List<Personagem>> listar(
            @RequestParam(required = false) String nome, 
            @RequestParam(required = false) String referencia) {
        List<Personagem> personagens = new ArrayList<>();
        if (nome == null && referencia == null) {
            personagens = service.consultar();
        } else if (nome != null && referencia == null) {
            Personagem personagem = service.consultarPorNome(nome);
            personagens.add(personagem);
        } else {
            Personagem personagem = service.consultarPor(nome, referencia);
            personagens.add(personagem);
        }
        return ResponseEntity.ok().body(personagens);
    }

    @GetMapping("{id}")
    public ResponseEntity<Personagem> consultarPorId(@PathVariable Integer id) {
        var personagem = service.consultarPorId(id);
        return ResponseEntity.ok().body(personagem);
    }

    @PostMapping
    public ResponseEntity<Personagem> criar(@RequestBody Personagem personagem) {
        personagem = service.inserir(personagem);
        return ResponseEntity.created(URI.create(personagem.getId().toString())).body(personagem);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> excluir(@PathVariable Integer id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
    
}
