package com.senai.personagem.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.senai.personagem.model.Personagem;
import com.senai.personagem.service.PersonagemService;

@RestController
@RequestMapping("personagens")
public class PersonagemController {

    @Autowired
    private PersonagemService service;


    @GetMapping
    public ResponseEntity<List<Personagem>> listar() {
        var personagens = service.consultar();
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
