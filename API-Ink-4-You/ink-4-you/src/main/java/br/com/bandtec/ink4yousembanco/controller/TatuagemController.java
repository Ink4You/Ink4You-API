package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.model.Tatuagem;
import br.com.bandtec.ink4yousembanco.repository.TatuadorRepository;
import br.com.bandtec.ink4yousembanco.repository.TatuagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/tatuagens")
public class TatuagemController {

    @Autowired
    private TatuagemRepository repository;


    // Buscar todas as tatuagens
    @GetMapping
    public ResponseEntity findAllTatuagens(){
        List<Tatuagem> tatuagens = repository.findAll();

        if (!tatuagens.isEmpty()){
            return ResponseEntity.status(200).body(tatuagens);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/limit/{quantidade}")
    public ResponseEntity findTopTatuagens(@PathVariable Integer quantidade){
        List<Tatuagem> tatuagens = repository.findLimit(quantidade);
        if (!tatuagens.isEmpty()){
            return ResponseEntity.status(200).body(tatuagens);
        }
        return ResponseEntity.status(204).build();
    }
    // Buscar tatuagem por id
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findByIdTatuagem(@PathVariable Integer id){
        return repository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    // Buscar tatuagens por id de tatuador
    @GetMapping("/tatuador/{id}")
    public ResponseEntity findByIdTatuadorFotos(@PathVariable Integer id){

        List<Tatuagem> tatuagens = repository.findByIdTatuador(id);

        if (!tatuagens.isEmpty()){
            return ResponseEntity.status(200).body(tatuagens);
        }

        return ResponseEntity.status(204).build();
    }


    // adicionar uma nova tatuagem ao banco
    @PostMapping
    public ResponseEntity adicionarTatuagem(@RequestBody Tatuagem tatuagem) {
        repository.save(tatuagem);
        return ResponseEntity.status(201).body(tatuagem);
    }


    // Endpoint para deletar uma tatuagem por id
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTatuagem(@PathVariable Integer id){
        if (repository.existsById(id)){
            repository.deleteById(id);
            return ResponseEntity.status(200).build();
        }
        return ResponseEntity.status(404).build();
    }


    @PatchMapping("/foto/{id}")
    public ResponseEntity patchFoto(
            @PathVariable int id,
            @RequestBody MultipartFile file
    ) throws IOException {
        if (repository.existsById(id)) {
            Tatuagem tatuagem =
                    repository.findById(id).get();
            byte[] novaFoto = file.getBytes();
            tatuagem.setSrc_imagem(novaFoto);
            repository.save(tatuagem);
            return ResponseEntity.status(200).build();
        } else {
            return ResponseEntity.status(404).build();
        }
    }


    // Alterar uma tatuagem existente por id
    @PutMapping(value="/{id}")
    public ResponseEntity updateTatuagemById(@PathVariable("id") Integer id,
                                             @RequestBody Tatuagem tatuagem){
        return repository.findById(id)
                .map(record -> {
                    record.setTitulo(tatuagem.getTitulo());
                    record.setLocal_tatuagem(tatuagem.getLocal_tatuagem());
                    record.setDescricao(tatuagem.getDescricao());
                    record.setSrc_imagem(tatuagem.getSrc_imagem());
                    record.setId_tatuador(tatuagem.getId_tatuador());
                    Tatuagem update = repository.save(record);
                    return ResponseEntity.ok().body(update);
                }).orElse(ResponseEntity.notFound().build());
    }



}
