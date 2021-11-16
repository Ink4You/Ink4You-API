package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.model.Usuario;
import br.com.bandtec.ink4yousembanco.repository.UsuarioRepository;
import br.com.bandtec.ink4yousembanco.uteis.CsvAdapter;
import br.com.bandtec.ink4yousembanco.uteis.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;


@CrossOrigin("*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repositoryUsuario;

    // métodos do CRUD de usuário:

    // Endpoint de busca de Usuarios (Todos)
    @GetMapping
    public ResponseEntity findUsuarios(){
        List<Usuario> usuarios = repositoryUsuario.findAll();
        LocalDate now = LocalDate.now();

        if (usuarios != null){
            for (int i = 0; i < usuarios.size(); i++){

                LocalDate nascimento =  usuarios.get(i).getData_nascimento();

                if ((nascimento != null)){
                    int years = Period.between(nascimento, now).getYears();
                    usuarios.get(i).setIdade(years);
                }
            }
            return ResponseEntity.status(200).body(usuarios);
        }
        return ResponseEntity.status(401).build();
    }


    // Endpoint de busca de usuario por ID
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findByIdCliente(@PathVariable Integer id){
        return repositoryUsuario.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    // POST de cadastro de usuario
    @PostMapping("/cadastro-usuario")
    public ResponseEntity cadastroUsuario(@RequestBody Usuario user){
        repositoryUsuario.save(user);
        return ResponseEntity.status(201).body(user);
    }


    // EndPoint de alteração de dados do usuario por ID
    @PutMapping(value="/{id}")
    public ResponseEntity updateUsuario(@PathVariable("id") Integer id,
                                 @RequestBody Usuario user) {
        return repositoryUsuario.findById(id)
                .map(record -> {
                    record.setNome(user.getNome());
                    record.setData_nascimento(user.getData_nascimento());
                    record.setCpf(user.getCpf());
                    record.setCep(user.getCep());
                    record.setTelefone(user.getTelefone());
                    record.setEmail(user.getEmail());
                    record.setSenha(user.getSenha());
                    record.setFoto_perfil(user.getFoto_perfil());
                    Usuario updated = repositoryUsuario.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


    // Endpoint para deletar o usuario por ID
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> deleteUsuario(@PathVariable Integer id) {
        return repositoryUsuario.findById(id)
                .map(record -> {
                    repositoryUsuario.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


    // Endpoint de login (autenticação) do usuario
    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity autenticacaoTatuador(@PathVariable String email, @PathVariable String senha){

        Usuario autendicado =  repositoryUsuario.findByEmailAndSenha(email, senha);

        LocalDate nascimento = autendicado.getData_nascimento();
        LocalDate now = LocalDate.now();

        if (autendicado != null){
            int years = Period.between(nascimento, now).getYears();
            autendicado.setIdade(years);
            return ResponseEntity.status(200).body(autendicado);
        }

        return ResponseEntity.status(401).build();

    }


    // Endpoint para gerar o relatorio em csv e fazer o download
    @GetMapping("/relatorio-usuarios.csv")
    public void getCsvUsuario(HttpServletResponse response) throws IOException {

        List<Usuario> listaJava = repositoryUsuario.findAll();

        ListaObj<Usuario> lista = new ListaObj<>(listaJava.size());

        for (int i = 0; i < repositoryUsuario.count(); i++) {
            lista.adicionar(listaJava.get(i));
        }

        response.setContentType("text/csv");
        CsvAdapter.downloadCsvUsuario(response.getWriter(), lista);
        response.setStatus(200);

    }

}
