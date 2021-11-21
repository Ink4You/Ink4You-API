package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.InstagramImagem;
import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.repository.InstagramImageRepository;
import br.com.bandtec.ink4yousembanco.repository.TatuadorRepository;
import br.com.bandtec.ink4yousembanco.uteis.ResponseWebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/instagram")
public class InstagramImageController {

    @Autowired
    private InstagramImageRepository repositoryInstagram;
    @Autowired
    private TatuadorRepository repositoryTatuador;

    @GetMapping("/buscar-fotos/{account}")
    public ResponseEntity getInstagramImages(@PathVariable String account) {
        //http://localhost:8080/instagram/buscar-fotos/rafanildsz

        //List<String> instagramImagesSrc = new ArrayList<>();
        ResponseWebScraper response = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.getForObject(String.format("http://localhost:3000/insta/%s",account), ResponseWebScraper.class);
        } catch(Exception err) {
            return ResponseEntity.status(503).build();
        }

        if(response.getData().isEmpty()) {
            return ResponseEntity.status(204).body(response);
        }

        return ResponseEntity.ok().body(response);

    }

    @PostMapping("/cadastrar-foto/")
    public ResponseEntity postInstagramImage(@RequestBody InstagramImagem instagramImagem) {
        repositoryInstagram.save(instagramImagem);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/atualizar-imagens/{idTatuador}")
    public ResponseEntity updateInstagramImages(@PathVariable Integer idTatuador) {
        System.out.println("teste");
        if (idTatuador <= 0) {
            ResponseEntity.status(404).build();
        } else {
            List<String> images = new ArrayList<>();
            Tatuador tatuador = repositoryTatuador.findById(idTatuador).get();
            String account = tatuador.getConta_instagram();

            ResponseEntity response = getInstagramImages(account);
            if (response.getStatusCodeValue() != 200) {
                return ResponseEntity.status(404).build();
            }

            ResponseWebScraper responseBody = (ResponseWebScraper) response.getBody();
            images.addAll(responseBody.getData());

//            for (String image : images) {
//                if (!repositoryInstagram.existsByImage(image)) {

                    postInstagramImage(new InstagramImagem(tatuador.getId_tatuador(),images.get(0)));
//                }
//            }
            return ResponseEntity.status(201).body(images);
        }
        return ResponseEntity.status(503).build();
    }

}
