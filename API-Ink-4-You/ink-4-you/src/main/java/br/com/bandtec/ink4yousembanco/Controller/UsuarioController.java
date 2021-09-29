package br.com.bandtec.ink4yousembanco.Controller;

import br.com.bandtec.ink4yousembanco.model.Usuario;
import br.com.bandtec.ink4yousembanco.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


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
    @PostMapping("cadastro-usuario")
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


}
