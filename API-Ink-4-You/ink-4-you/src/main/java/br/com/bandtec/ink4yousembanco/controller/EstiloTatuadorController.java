package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Estilo;
import br.com.bandtec.ink4yousembanco.model.EstiloTatuador;
import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.repository.EstiloRepository;
import br.com.bandtec.ink4yousembanco.repository.EstiloTatuadorRepository;
import br.com.bandtec.ink4yousembanco.repository.TatuadorRepository;
import br.com.bandtec.ink4yousembanco.uteis.FilaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/estilo-tatuador")
public class EstiloTatuadorController {

    @Autowired
    private EstiloTatuadorRepository repository;

    @Autowired
    private EstiloRepository estiloRepository;

    @Autowired
    private TatuadorRepository tatuadorRepository;

    @PostMapping("/atualiza-estilos/{id_tatuador}")
    public ResponseEntity adicionarEstiloTatuador(@PathVariable Integer id_tatuador, @RequestBody List<Integer> id_estilos ){
        List<EstiloTatuador> estilos = repository.findAll();

        FilaObj<Integer> fila = new FilaObj(id_estilos.size());
        for(int x = 0 ; x < id_estilos.size(); x++){
            fila.insert(id_estilos.get(x));
        }

        for(int x = 0; x < estilos.size(); x++){
            if(estilos.get(x).getId_tatuador() != null){
                if(estilos.get(x).getId_tatuador().equals(id_tatuador)) {
                    deletaEstiloUsuario(estilos.get(x).getId());
                }
            }
        }

        for(int x =0; x < id_estilos.size() ; x++){
            EstiloTatuador estiloTatuador = new EstiloTatuador(id_tatuador, fila.poll());
            repository.save(estiloTatuador);
        }
        return ResponseEntity.status(201).build();
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> deletaEstiloUsuario(@PathVariable Integer id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        }

        return ResponseEntity.status(404).build();
    }


    @GetMapping("/")
    public ResponseEntity findAllEstiloTatuador(){
        List<EstiloTatuador> lista = repository.findAll();
        if(lista.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(lista);
    }

    @GetMapping("/id-tatuador/{id}")
    public ResponseEntity findEstiloByIdTatuador(@PathVariable Integer id){
        List<EstiloTatuador> estilosTatuadores = repository.findAll();
        List<Integer> idEstilos = new ArrayList<>();
        List<Optional<Estilo>> estiloTatuador = new ArrayList<>();

        for(int x = 0; x < estilosTatuadores.size(); x++){
            if(estilosTatuadores.get(x).getId_tatuador().equals(id)){
                idEstilos.add(estilosTatuadores.get(x).getId_estilo());
            }
        }

        for(Integer idEstilo : idEstilos){
            Optional<Estilo> estilo = estiloRepository.findById(idEstilo);
            estiloTatuador.add(estilo);
        }

        if(estiloTatuador.isEmpty()) return ResponseEntity.status(204).build();
        return ResponseEntity.status(200).body(estiloTatuador);
    }


    @GetMapping("/id-estilo/{id}")
    public ResponseEntity findTatuadorbyEstilo(@PathVariable Integer id){
        List<EstiloTatuador> estilosTatuadores = repository.findAll();
        List<Integer> idTatuadores = new ArrayList<>();
        List<Optional<Tatuador>> estiloTatuador = new ArrayList<>();

        Integer[] idEstilos;
        for(int x = 0; x < estilosTatuadores.size(); x++){
            if(estilosTatuadores.get(x).getId_estilo().equals(id)){
                idTatuadores.add(estilosTatuadores.get(x).getId_tatuador());
            }
        }

        for(Integer idTatuador : idTatuadores){
            Optional<Tatuador> tatuador = tatuadorRepository.findById(idTatuador);
            estiloTatuador.add(tatuador);
        }
        if(estiloTatuador.isEmpty()) return ResponseEntity.status(204).build();

        return ResponseEntity.status(200).body(estiloTatuador);
    }

}


