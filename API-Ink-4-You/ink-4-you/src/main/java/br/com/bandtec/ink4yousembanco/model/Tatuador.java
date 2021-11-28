package br.com.bandtec.ink4yousembanco.model;

import br.com.bandtec.ink4yousembanco.uteis.InstagramAdapter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tatuador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tatuador;

    private String nome;
    private String username;
    private LocalDate data_nascimento;
    private String cnpj;
    private String cep;
    private String logradouro;
    private String numero_logradouro;
    private String telefone;
    private String email;
    private String senha;
    private String conta_instagram;
    private String foto_perfil;
    private String uf;
    @Transient
    private Integer idade;
    private String sobre;

    public Tatuador(Integer id_tatuador, String nome, String username, LocalDate data_nascimento, String cnpj, String cep, String logradouro, String numero_logradouro, String telefone, String email, String senha, String conta_instagram, String foto_perfil, String uf) {
        this.id_tatuador = id_tatuador;
        this.nome = nome;
        this.username = username;
        this.data_nascimento = data_nascimento;
        this.cnpj = cnpj;
        this.cep = cep;
        this.logradouro = logradouro;
        this.numero_logradouro = numero_logradouro;
        this.telefone = telefone;
        this.email = email;
        this.senha = senha;
        this.conta_instagram = conta_instagram;
        this.foto_perfil = foto_perfil;
        this.uf = uf;
        this.idade = 0;
        this.sobre = "";
    }
}
