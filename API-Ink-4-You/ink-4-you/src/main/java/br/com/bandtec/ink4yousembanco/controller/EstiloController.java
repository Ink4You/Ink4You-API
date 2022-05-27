package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.dto.CreateRequestEstiloDto;
import br.com.bandtec.ink4yousembanco.model.Estilo;
import br.com.bandtec.ink4yousembanco.model.Tatuagem;
import br.com.bandtec.ink4yousembanco.repository.EstiloRepository;
import br.com.bandtec.ink4yousembanco.repository.TatuagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/estilo")
public class EstiloController {

    @Autowired
    private EstiloRepository repository;
    @Autowired
    private TatuagemRepository tatuagemRepository;

    @GetMapping()
    public ResponseEntity getEstilos(){
        List<Estilo> estiloLista = repository.findAll();
        if(estiloLista.isEmpty()) return ResponseEntity.status(404).build();
        return ResponseEntity.status(200).body(estiloLista);
    }

    @GetMapping("/estilos-com-id")
    public ResponseEntity getIdAndTitulo(){
        List<Estilo> estiloLista = repository.findAll();
        List<CreateRequestEstiloDto> estiloDtoLista = new ArrayList<>();

        for (int i = 0; i < estiloLista.size(); i++){
            CreateRequestEstiloDto estilo = new CreateRequestEstiloDto(estiloLista.get(i).getId_estilo(), estiloLista.get(i).getTitulo());
            estiloDtoLista.add(estilo);
        }

        if (estiloDtoLista.isEmpty()){
            return ResponseEntity.status(204).build();
        }

        return ResponseEntity.status(200).body(estiloDtoLista);
    }

    @GetMapping("/populares")
    public ResponseEntity getEstilosPopulares(){
        List<Tatuagem> tatuagems = tatuagemRepository.findAll();
        Map<Integer, Long> mapEstilos = tatuagems.stream()
                .collect(Collectors.groupingBy(Tatuagem::getId_estilo, Collectors.counting()));
        List<Integer> idEstilos = new ArrayList<Integer>(mapEstilos.keySet());
        System.out.println(idEstilos);

        Collections.sort(idEstilos, Collections.reverseOrder());

        List<Optional<Estilo>> estilos = new ArrayList<>();
        for(int i = 0; i < 4; i++){
            estilos.add(repository.findById(idEstilos.get(i)));
        }
        return ResponseEntity.status(200).body(estilos);
    }
}
