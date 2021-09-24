package br.com.bandtec.ink4yousembanco.Controller;

import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.repository.TatuadorRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tatuadores")
public class TatuadorController {

    private TatuadorRepository repositoryTatuador;

    TatuadorController(TatuadorRepository repositoryTatuador) {
        this.repositoryTatuador = repositoryTatuador;
    }


    // métodos do CRUD de Tatuador aqui:


    // Endpoint de busca de Tatuadores (Todos)
    @GetMapping
    public List findTatuadores(){
        return repositoryTatuador.findAll();
    }


    // Endpoint de busca de Tatuador por ID
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findByIdTatuador(@PathVariable long id){
        return repositoryTatuador.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    // POST de cadastro de Tatuador
    @PostMapping("cadastro-tatuador")
    public Tatuador cadastroTatuador(@RequestBody Tatuador tatu){
        return repositoryTatuador.save(tatu);
    }



    // EndPoint de alteração de dados do Tatuador
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Tatuador tatu) {
        return repositoryTatuador.findById(id)
                .map(record -> {
                    record.setNome(tatu.getNome());
                    record.setEmail(tatu.getEmail());
                    record.setSenha(tatu.getSenha());
                    record.setCelular(tatu.getCelular());
                    record.setCep(tatu.getCep());
                    record.setDataNascimento(tatu.getDataNascimento());
                    record.setFotoDePerfil(tatu.getFotoDePerfil());
                    record.setUserName(tatu.getUserName());
                    record.setContaInstagram(tatu.getContaInstagram());
                    record.setCnpj(tatu.getCnpj());
                    record.setLogradouro(tatu.getLogradouro());
                    record.setLogradouroNumero(tatu.getLogradouroNumero());
                    Tatuador updated = repositoryTatuador.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }



    // Endpoint para deletar o Tatuador por ID
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return repositoryTatuador.findById(id)
                .map(record -> {
                    repositoryTatuador.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
