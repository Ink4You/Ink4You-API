package br.com.bandtec.ink4yousembanco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
//@Table(name="instagramImagem")
public class Instagram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Integer id_tatuador;
    private String imagem;
}
