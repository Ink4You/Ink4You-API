package br.com.bandtec.ink4yousembanco.Controller;

import br.com.bandtec.ink4yousembanco.model.Cliente;
import br.com.bandtec.ink4yousembanco.repository.ClienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private ClienteRepository repositoryCliente;

    ClienteController(ClienteRepository repositoryCliente) {
        this.repositoryCliente = repositoryCliente;
    }


    // métodos do CRUD de Cliente aqui:

    // Endpoint de busca de Clientes (Todos)
    @GetMapping
    public List findClientes(){
        return repositoryCliente.findAll();
    }


    // Endpoint de busca de cliente por ID
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findByIdCliente(@PathVariable long id){
        return repositoryCliente.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    // POST de cadastro de cliente
    @PostMapping("cadastro-cliente")
    public Cliente cadastroCliente(@RequestBody Cliente cli){
        return repositoryCliente.save(cli);
    }


    // EndPoint de alteração de dados do Cliente por ID
    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id,
                                 @RequestBody Cliente cli) {
        return repositoryCliente.findById(id)
                .map(record -> {
                    record.setNome(cli.getNome());
                    record.setEmail(cli.getEmail());
                    record.setSenha(cli.getSenha());
                    record.setCelular(cli.getCelular());
                    record.setCep(cli.getCep());
                    record.setDataNascimento(cli.getDataNascimento());
                    record.setFotoDePerfil(cli.getFotoDePerfil());
                    record.setCpf(cli.getCpf());
                    Cliente updated = repositoryCliente.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }



    // Endpoint para deletar o Cliente por ID
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return repositoryCliente.findById(id)
                .map(record -> {
                    repositoryCliente.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


}
