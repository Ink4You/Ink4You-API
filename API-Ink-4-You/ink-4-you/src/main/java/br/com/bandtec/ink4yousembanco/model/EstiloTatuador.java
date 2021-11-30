package br.com.bandtec.ink4yousembanco.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class EstiloTatuador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer id_estilo;
    private Integer id_tatuador;

    public EstiloTatuador(Integer id_tatuador, Integer id_estilo) {
        this.id_tatuador = id_tatuador;
        this.id_estilo = id_estilo;
    }
}