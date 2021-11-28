package br.com.bandtec.ink4yousembanco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

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
    private Integer id_tatuador;
}
