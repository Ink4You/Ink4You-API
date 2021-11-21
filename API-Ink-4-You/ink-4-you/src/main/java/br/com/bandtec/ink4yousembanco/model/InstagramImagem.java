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
public class InstagramImagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer id_tatuador;
    private String imagem;

    public InstagramImagem(Integer id_tatuador, String imagem) {
        this.id_tatuador = id_tatuador;
        this.imagem = imagem;
    }
}
