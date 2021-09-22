package br.com.bandtec.ink4yousembanco;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private List<Usuario> usuarios = new ArrayList<>();

    @GetMapping("/cadastrar-cliente/{email}/{nome}/{celular}/{cep}/{dataNascimento}/{cpf}/{senha}/{fotoPerfil}")
    public String cadastrarCliente(@PathVariable String email,
                                 @PathVariable String nome,
                                 @PathVariable long celular,
                                 @PathVariable String cep,
                                 @PathVariable String dataNascimento,
                                 @PathVariable String cpf,
                                 @PathVariable String senha,
                                 @PathVariable String fotoPerfil) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");


        Cliente cli = new Cliente(email, nome, senha, celular, cep, formato.parse(dataNascimento), cpf, fotoPerfil);
        usuarios.add(cli);

        return String.format("Usuario %s cadastrado com sucesso", cli.getNome());

//   http://localhost:8080/usuario/cadastrar-cliente/joao@gmail.com/joao/11907458612/05588908/09-01-2000/59831578904/joao123/ghvh578gf

    }

    @GetMapping("/cadastrar-tatuador/{email}/{nome}/{userName}/{celular}/{cep}/{dataNascimento}/{logradouro}/{logradouroNumero}/{cnpj}/{contaInstagram}/{senha}/{fotoPerfil}")
    public String cadastrarTatuador(@PathVariable String email,
                                  @PathVariable String nome,
                                  @PathVariable String userName,
                                  @PathVariable long celular,
                                  @PathVariable String cep,
                                  @PathVariable String dataNascimento,
                                  @PathVariable String logradouro,
                                  @PathVariable String logradouroNumero,
                                  @PathVariable String cnpj,
                                  @PathVariable String contaInstagram,
                                  @PathVariable String senha,
                                  @PathVariable String fotoPerfil) throws ParseException {

        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");

        Tatuador tatu = new Tatuador(userName, email, nome, senha, celular, logradouro, logradouroNumero, cep, formato.parse(dataNascimento), contaInstagram, cnpj, fotoPerfil);

        usuarios.add(tatu);

        return String.format("Usuario %s cadastrado com sucesso", tatu.getNome());

//    http://localhost:8080/usuario/cadastrar-tatuador/tatuador@gmail.com/ricardo/ricardo123/11968093452/07758769/10-05-2000/rua-dos-tatuadores/260/47689098635790/ricardotatuador/ricardotatu/fyewyucei
    }


    @GetMapping("/exibir")
    public List<Usuario> exibirTodos(){
        return usuarios;
    }


    @GetMapping("/logar/{email}/{senha}")
    public String logar(@PathVariable String email, @PathVariable String senha) {

        for (Usuario user: usuarios){
            if (user.getEmail().equals(email) && user.senha.equals(senha)){
                return String.format("Usuário %s logado com sucesso", user.getNome());
            }
        }

        return String.format("E-mail ou senha invalido");


//   http://localhost:8080/usuario/logar/joao@gmail.com/joao123


    }


    @GetMapping("/logoff/{email}")
    public String logoff(@PathVariable String email) {

        for (Usuario user: usuarios){
            if (user.getEmail().equals(email)) {
                return String.format("O usuário %s saiu do sistema", user.getNome());
            }
        }

        return String.format("Este usuário não é válido");

        //   http://localhost:8080/usuario/logoff/joao@gmail.com/


    }


}
