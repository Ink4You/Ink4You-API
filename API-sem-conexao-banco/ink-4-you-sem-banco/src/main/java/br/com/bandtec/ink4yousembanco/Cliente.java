package br.com.bandtec.ink4yousembanco;

import java.util.Date;

public class Cliente extends Usuario {

    private String cpf;

    public Cliente(String email, String nome, String senha,
                   long celular, String cep, Date dataNascimento, String cpf, String fotoPerfil) {
        super(email, nome, senha, celular, cep, dataNascimento, fotoPerfil);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }
}
