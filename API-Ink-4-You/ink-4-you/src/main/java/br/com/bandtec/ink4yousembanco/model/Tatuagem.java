package br.com.bandtec.ink4yousembanco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Tatuagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_tatuagem;

    private String titulo;
    private String local_tatuagem;
    private String descricao;
    private String src_imagem;
    private String destaque;
    private Integer id_tatuador;

}
