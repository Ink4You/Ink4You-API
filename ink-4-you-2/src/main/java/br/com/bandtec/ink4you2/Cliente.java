package br.com.bandtec.ink4you2;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;

public class Cliente extends Usuario implements Autenticavel{

    private String cpf;
    Conexao con;
    JdbcTemplate template;

    public Cliente(String email, String nome, String senha,
                   long celular, String cep, Date dataNascimento, String cpf, String fotoPerfil) {
        super(email, nome, senha, celular, cep, dataNascimento, fotoPerfil);
        this.cpf = cpf;
    }


    @Override
    public void cadastrarUsuario() {
        String queryUsuario = "INSERT INTO Usuario VALUES (nome, dataNascimento, cpf, cep, telefone, email, senha, fotoPerfil)";

        template.update(queryUsuario, super.getNome(), super.getDataNascimento(), cpf, super.getCep(), super.getCelular(),
                super.getEmail(), super.getSenha(), super.getFotoDePerfil());
    }

    @Override
    public void logar(String email, String senha) {

    }

    @Override
    public void logoff(Integer userID) {

    }

    public String getCpf() {
        return cpf;
    }
}
