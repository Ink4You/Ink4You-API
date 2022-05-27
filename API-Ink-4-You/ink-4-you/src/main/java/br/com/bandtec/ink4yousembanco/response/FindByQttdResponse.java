package br.com.bandtec.ink4yousembanco.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class FindByQttdResponse {
    Integer idTatuagem = 0;
    String tituloTatuagem = "";
    String srcImagem;
    int idTatuador = 0;
    String tatuadorNome = "";
    String tatuadorUf = "";
    String tatuadorFotoPerfil;
};
