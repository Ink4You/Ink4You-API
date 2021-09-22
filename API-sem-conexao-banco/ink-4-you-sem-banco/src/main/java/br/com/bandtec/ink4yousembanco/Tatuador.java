package br.com.bandtec.ink4yousembanco;

import java.util.Date;

public class Tatuador extends Usuario{

    private String userName;
    private String contaInstagram;
    private String cnpj;
    private String logradouro;
    private String logradouroNumero;

    public Tatuador(String userName, String email, String nome, String senha,
                    long celular, String logradouro, String logradouroNumero,
                    String cep, Date dataNascimento, String contaInstagram, String cnpj, String fotoPerfil) {

        super(email, nome, senha, celular, cep, dataNascimento, fotoPerfil);
        this.userName = userName;
        this.contaInstagram = contaInstagram;
        this.cnpj = cnpj;
        this.logradouro = logradouro;
        this.logradouroNumero = logradouroNumero;
    }


    public String getUserName() {
        return userName;
    }

    public String getContaInstagram() {
        return contaInstagram;
    }

    public String getCnpj() {
        return cnpj;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getLogradouroNumero() {
        return logradouroNumero;
    }
}
