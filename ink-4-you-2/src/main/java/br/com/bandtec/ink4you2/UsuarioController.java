package br.com.bandtec.ink4you2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("usuario")
public class UsuarioController {


    @GetMapping("/cadastrar-cliente/{email}/{nome}/{celular}/{cep}/{dataNascimento}/{cpf}/{senha}/{fotoPerfil}")
    public void cadastrarCliente(@PathVariable String email,
                                 @PathVariable String nome,
                                 @PathVariable long celular,
                                 @PathVariable String cep,
                                 @PathVariable Date dataNascimento,
                                 @PathVariable String cpf,
                                 @PathVariable String senha,
                                 @PathVariable String fotoPerfil) {

        Cliente cli = new Cliente(email, nome, senha, celular, cep, dataNascimento, cpf, fotoPerfil);
        cli.cadastrarUsuario();
    }

    @GetMapping("/cadastrar-tatuador/{email}/{nome}/{celular}/{cep}/{dataNascimento}/{senha}")
    public void cadastrarTatuador(@PathVariable String email,
                                  @PathVariable String nome,
                                  @PathVariable long celular,
                                  @PathVariable String cep,
                                  @PathVariable Date dataNascimento,
                                  @PathVariable String senha) {

    }


    @GetMapping("/logar/{email}/{senha}")
    public void logar(@PathVariable String email, @PathVariable String senha) {

    }


    @GetMapping("/logoff/{userId}")
    public void logoff(@PathVariable Integer userId) {

    }

}