package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.repository.TatuadorRepository;
import br.com.bandtec.ink4yousembanco.uteis.CsvAdapter;
import br.com.bandtec.ink4yousembanco.uteis.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("tatuadores")
public class TatuadorController {

    @Autowired
    private TatuadorRepository repositoryTatuador;

    // métodos do CRUD de Tatuador aqui:

    // Endpoint de busca de Tatuadores (Todos)
    @GetMapping
    public List findTatuadores(){
        return repositoryTatuador.findAll();
    }


    // Endpoint de busca de Tatuador por ID
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findByIdTatuador(@PathVariable Integer id){
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
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestBody Tatuador tatu) {
        return repositoryTatuador.findById(id)
                .map(record -> {
                    record.setNome(tatu.getNome());
                    record.setUsername(tatu.getUsername());
                    record.setData_nascimento(tatu.getData_nascimento());
                    record.setCnpj(tatu.getCnpj());
                    record.setCep(tatu.getCep());
                    record.setLogradouro(tatu.getLogradouro());
                    record.setNumero_logradouro(tatu.getNumero_logradouro());
                    record.setTelefone(tatu.getTelefone());
                    record.setEmail(tatu.getEmail());
                    record.setSenha(tatu.getSenha());
                    record.setConta_instagram(tatu.getConta_instagram());
                    record.setFoto_perfil(tatu.getFoto_perfil());
                    Tatuador updated = repositoryTatuador.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


    // Endpoint para deletar o Tatuador por ID
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable Integer id) {
        return repositoryTatuador.findById(id)
                .map(record -> {
                    repositoryTatuador.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


    // Endpoint de login (autenticação) do tatuador
    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity autenticacaoTatuador(@PathVariable String email, @PathVariable String senha){

        Tatuador autendicado =  repositoryTatuador.findByEmailAndSenha(email, senha);

        if (autendicado == null){
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.status(200).body(autendicado);

    }

    // Endpoint para gerar o relatorio em csv e fazer o download
    @GetMapping("/relatorio-tatuadores.csv")
    public void getCsvTatuador(HttpServletResponse response) throws IOException {

        List<Tatuador> listaJava = repositoryTatuador.findAll();

        ListaObj<Tatuador> lista = new ListaObj<>(listaJava.size());

        for (int i = 0; i < repositoryTatuador.count(); i++) {
            lista.adicionar(listaJava.get(i));
        }

        response.setContentType("text/csv");
        CsvAdapter.downloadCsvTatuador(response.getWriter(), lista);
        response.setStatus(200);

    }

}
