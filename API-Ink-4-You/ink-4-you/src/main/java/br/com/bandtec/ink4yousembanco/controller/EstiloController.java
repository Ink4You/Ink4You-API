package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Estilo;
import br.com.bandtec.ink4yousembanco.repository.EstiloRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/estilo")
public class EstiloController {

    @Autowired
    private EstiloRepository repository;

    @GetMapping()
    public ResponseEntity getEstilos(){
        List<Estilo> estiloLista = repository.findAll();

        if(estiloLista.isEmpty()) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(estiloLista);
    }

}
