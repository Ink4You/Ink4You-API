package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Relato;
import br.com.bandtec.ink4yousembanco.repository.RelatoRepository;
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
    public Relato postarRelato(@RequestBody Relato relato) {
        return repositoryRelato.save(relato);
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

        List<Relato> ultimosRegistros = new ArrayList<>();

        for (Integer x = relatos.size() - 1; x > relatos.size() - 7; x--){
            ultimosRegistros.add(relatos.get(x));
        }

       return ResponseEntity.status(200).body(ultimosRegistros);

    }
}


