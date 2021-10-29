package br.com.bandtec.ink4yousembanco.model;

import br.com.bandtec.ink4yousembanco.uteis.InstagramAdapter;
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
public class Tatuador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tatuador;

    private String nome;
    private String username;
    private Date data_nascimento;
    private String cnpj;
    private String cep;
    private String logradouro;
    private String numero_logradouro;
    private String telefone;
    private String email;
    private String senha;
    private String conta_instagram;
    private String foto_perfil;

}
