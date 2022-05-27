package br.com.bandtec.ink4yousembanco.dto;

public class CreateRequestEstiloDto {
    private Integer id_estilo;
    private String titulo;

    public CreateRequestEstiloDto(Integer id_estilo, String titulo) {
        this.id_estilo = id_estilo;
        this.titulo = titulo;
    }
}
