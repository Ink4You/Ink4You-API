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
    Integer id_tatuagem = 0;
    String titulo = "";
    String src_imagem;
    int id_tatuador = 0;
    String nome = "";
    String uf = "";
    String foto_perfil = "";
};
