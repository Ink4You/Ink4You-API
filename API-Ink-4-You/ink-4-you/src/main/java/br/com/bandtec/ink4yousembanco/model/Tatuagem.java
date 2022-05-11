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
    private byte[] src_imagem;
    private Integer id_tatuador;
    private Integer id_estilo;

    public Tatuagem(Integer id_tatuagem, String titulo, String local_tatuagem, String descricao, Integer id_tatuador, Integer id_estilo) {
        this.id_tatuagem = id_tatuagem;
        this.titulo = titulo;
        this.local_tatuagem = local_tatuagem;
        this.descricao = descricao;
        this.id_tatuador = id_tatuador;
        this.id_estilo = id_estilo;
    }
}
