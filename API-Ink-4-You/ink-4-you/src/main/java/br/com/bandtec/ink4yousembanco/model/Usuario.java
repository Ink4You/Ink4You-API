package br.com.bandtec.ink4yousembanco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    private String nome;
    private LocalDate data_nascimento;
    private String cpf;
    private String cep;
    private String telefone;
    private String email;
    private String senha;
    private byte[] foto_perfil;
    @Transient
    private Integer idade;

    public Usuario(Integer id_usuario, String nome, LocalDate data_nascimento, String cpf, String cep, String telefone, String email, String senha, byte[] foto_perfil) {
        this.id_usuario = id_usuario;
        this.nome = nome;
        this.data_nascimento = data_nascimento;
        this.cpf = cpf;
        this.cep = cep;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.foto_perfil = foto_perfil;
        this.idade = 0;
    }
}

