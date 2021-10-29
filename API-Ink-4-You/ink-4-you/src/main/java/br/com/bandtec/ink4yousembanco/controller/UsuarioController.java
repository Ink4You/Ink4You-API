package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Usuario;
import br.com.bandtec.ink4yousembanco.repository.UsuarioRepository;
import br.com.bandtec.ink4yousembanco.uteis.CsvAdapter;
import br.com.bandtec.ink4yousembanco.uteis.ListaObj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    public List findClientes(){
        return repositoryUsuario.findAll();
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
    public Usuario cadastroCliente(@RequestBody Usuario cli){
        return repositoryUsuario.save(cli);
    }


    // EndPoint de alteração de dados do usuario por ID
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") Integer id,
                                 @RequestBody Usuario use) {
        return repositoryUsuario.findById(id)
                .map(record -> {
                    record.setNome(use.getNome());
                    record.setData_nascimento(use.getData_nascimento());
                    record.setCpf(use.getCpf());
                    record.setCep(use.getCep());
                    record.setTelefone(use.getTelefone());
                    record.setEmail(use.getEmail());
                    record.setSenha(use.getSenha());
                    record.setFoto_perfil(use.getFoto_perfil());
                    Usuario updated = repositoryUsuario.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


    // Endpoint para deletar o usuario por ID
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable Integer id) {
        return repositoryUsuario.findById(id)
                .map(record -> {
                    repositoryUsuario.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


    // Endpoint de login (autenticação) do usuario
    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity autenticacaoUsuario(@PathVariable String email, @PathVariable String senha){

        Usuario autendicado = repositoryUsuario.findByEmailAndSenha(email, senha);

        if (autendicado == null){
            return ResponseEntity.status(401).build();
        }

        return ResponseEntity.status(200).body(autendicado);

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
