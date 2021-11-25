package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Relato;
import br.com.bandtec.ink4yousembanco.repository.RelatoRepository;
import br.com.bandtec.ink4yousembanco.uteis.PilhaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/relatos")
public class RelatoController {

    @Autowired
    private RelatoRepository repositoryRelato;


    // Endpoint para postar um relato
    @PostMapping("/inserir-relato")
    public ResponseEntity postarRelato(@RequestBody Relato relato) {
        repositoryRelato.save(relato);
        return ResponseEntity.status(201).build();
    }

    // Endpoint para deletar o relato por id
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity<?> deleteRelato(@PathVariable Integer id) {
        return repositoryRelato.findById(id)
                .map(record -> {
                    repositoryRelato.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/buscar-relatos")
    public ResponseEntity<?> buscarRelato(){
        List<Relato> relatos = repositoryRelato.findAll();

        if(relatos.size() < 7){
            return ResponseEntity.status(200).body(relatos);
        }


        PilhaObj<Relato> ultimosRegistrosPilha = new PilhaObj<>(relatos.size());


            for (Integer i = relatos.size() - 1; i > relatos.size() - 7; i--){
                ultimosRegistrosPilha.push(relatos.get(i));
            }


        return ResponseEntity.status(200).body(ultimosRegistrosPilha);

    }
}



