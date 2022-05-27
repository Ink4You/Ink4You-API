package br.com.bandtec.ink4yousembanco.dto;

public class CreateRequestEstiloDto {
    private Integer idEstilo;
    private String nomeEstilo;

    public CreateRequestEstiloDto(Integer idEstilo, String nomeEstilo) {
        this.idEstilo = idEstilo;
        this.nomeEstilo = nomeEstilo;
    }

    public Integer getIdEstilo() {
        return idEstilo;
    }

    public void setIdEstilo(Integer idEstilo) {
        this.idEstilo = idEstilo;
    }

    public String getNomeEstilo() {
        return nomeEstilo;
    }

    public void setNomeEstilo(String nomeEstilo) {
        this.nomeEstilo = nomeEstilo;
    }
}
