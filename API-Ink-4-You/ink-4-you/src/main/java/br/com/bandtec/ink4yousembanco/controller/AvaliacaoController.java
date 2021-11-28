package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Avaliacao;
import br.com.bandtec.ink4yousembanco.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/avaliacao")
public class AvaliacaoController {

    @Autowired
    private AvaliacaoRepository repository;
    @PostMapping()
    public ResponseEntity postarAvaliacao(@RequestBody Avaliacao avaliacao){
        repository.save(avaliacao);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/{id_tatuador}")
    public ResponseEntity getAvaliacaoById(@PathVariable Integer id_tatuador){
        List<Avaliacao> lista = repository.findAll();
        List<Avaliacao> tatuadorAvaliacao = new ArrayList();
        for(Avaliacao avaliacao: lista){
            if(avaliacao.getId_tatuador() == id_tatuador){
                tatuadorAvaliacao.add(avaliacao);
            }
        }
        if (tatuadorAvaliacao.size() == 0) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(tatuadorAvaliacao);
    }

    @GetMapping("/media/{id_tatuador}")
    public ResponseEntity getMedia(@PathVariable Integer id_tatuador){
        List<Avaliacao> lista = repository.findAll();
        List<Integer> media = new ArrayList();
        Integer mediaCalculada =0;
        for(Avaliacao avaliacao: lista){
            if(avaliacao.getId_tatuador() == id_tatuador){
                media.add(avaliacao.getNota());
            }
        }

        for(Integer somaMedia: media){
            mediaCalculada+= somaMedia;
        }

        if (media.size() == 0) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(mediaCalculada / media.size());
    }

}
