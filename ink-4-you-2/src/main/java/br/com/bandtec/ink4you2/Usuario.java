package br.com.bandtec.ink4you2;

import java.util.Date;

public class Usuario{

    private Integer userId;
    private String email;
    private String nome;
    protected String senha;
    private long celular;
    private String cep;
    private Date dataNascimento;
    private String fotoDePerfil;

    public Usuario(String email, String nome, String senha,
                   long celular, String cep, Date dataNascimento, String fotoDePerfil) {
        this.email = email;
        this.nome = nome;
        this.senha = senha;
        this.celular = celular;
        this.dataNascimento = dataNascimento;
        this.cep = cep;
        this.fotoDePerfil = fotoDePerfil;
    }


    public Integer getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public long getCelular() {
        return celular;
    }

    public String getCep() {
        return cep;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public String getSenha() {
        return senha;
    }

    public String getFotoDePerfil() {
        return fotoDePerfil;
    }
}
