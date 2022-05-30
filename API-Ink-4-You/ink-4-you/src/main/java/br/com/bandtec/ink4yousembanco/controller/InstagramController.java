package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Instagram;
import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.repository.InstagramRepository;
import br.com.bandtec.ink4yousembanco.repository.TatuadorRepository;
import br.com.bandtec.ink4yousembanco.uteis.ResponseImgur;
import br.com.bandtec.ink4yousembanco.uteis.ResponseWebScraper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/instagram")
public class InstagramController {

    @Autowired
    private InstagramRepository repositoryInstagram;
    @Autowired
    private TatuadorRepository repositoryTatuador;

    @GetMapping
    public ResponseEntity getImages() {
        List<Instagram> instagramImages = repositoryInstagram.findAll();

        if (!instagramImages.isEmpty()){
            return ResponseEntity.status(200).body(instagramImages);
        }
        return ResponseEntity.status(204).build();
    }

    @GetMapping("/{id_tatuador}")
    public ResponseEntity getImages(@PathVariable Integer id_tatuador) {
        if (id_tatuador < 0 || id_tatuador == null) {
            return ResponseEntity.status(404).build();
        }

        Object[] images = repositoryInstagram.findImagemByIdTatuador(id_tatuador);

        return images != null
                ? ResponseEntity.status(200).body(images)
                : ResponseEntity.status(204).build();
    }

    @GetMapping("/buscar-fotos/{account}")
    public ResponseEntity getInstagramImages(@PathVariable String account) {
        ResponseWebScraper response = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.getForObject(String.format("http://localhost:3001/insta/%s",account), ResponseWebScraper.class);
        } catch(Exception err) {
            return ResponseEntity.status(503).build();
        }

        if(response.getData().isEmpty()) {
            return ResponseEntity.status(204).body(response);
        }

        return ResponseEntity.ok().body(response);

    }

    @PostMapping("/cadastrar-foto/")
    public ResponseEntity postInstagramImage(@RequestBody Instagram instagram) {
        repositoryInstagram.save(instagram);
        return ResponseEntity.status(201).build();
    }

    @GetMapping("/atualizar-imagens/{idTatuador}")
    public ResponseEntity updateInstagramImages(@PathVariable Integer idTatuador) {
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
            repositoryInstagram.deleteByIdTatuador(idTatuador);

            ResponseWebScraper responseBody = (ResponseWebScraper) response.getBody();
            images.addAll(responseBody.getData());


            for (int i = 0; i < images.size(); i++) {
                Instagram img = new Instagram();
                img.setId_tatuador(idTatuador);
                img.setImagem(images.get(i));
                try {
                    URL url = new URL(img.getImagem());
                    InputStream in = new BufferedInputStream(url.openStream());

                    byte[] foto = in.readAllBytes();
                    RestTemplate restTemplate = new RestTemplate();
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.MULTIPART_FORM_DATA);
                    headers.set("Authorization", "Client-id 241dc1dec32c2d8");
                    HttpEntity<byte[]> request = new HttpEntity<byte[]>(foto, headers);
                    ResponseEntity<ResponseImgur> responseImgur = restTemplate.postForEntity( "https://api.imgur.com/3/image", request , ResponseImgur.class );
                    img.setImagem(responseImgur.getBody().getData().getLink());
                } catch (IOException err) {
                    System.out.println(err);
                    return ResponseEntity.status(404).build();

                }

                postInstagramImage(img);
            }

            return ResponseEntity.status(201).build();
        }
        return ResponseEntity.status(503).build();
    }

}
