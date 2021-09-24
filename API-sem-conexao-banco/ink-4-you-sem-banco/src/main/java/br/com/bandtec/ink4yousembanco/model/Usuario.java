package br.com.bandtec.ink4yousembanco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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


    public void setEmail(String email) {
        this.email = email;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public void setFotoDePerfil(String fotoDePerfil) {
        this.fotoDePerfil = fotoDePerfil;
    }
}
