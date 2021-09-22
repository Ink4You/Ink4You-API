package br.com.bandtec.ink4you2;

import java.util.Date;

public class Tatuador extends Usuario implements Autenticavel{

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


    @Override
    public void cadastrarUsuario() {

    }

    @Override
    public void logar(String email, String senha) {

    }

    @Override
    public void logoff(Integer userID) {

    }


    public String getContaInstagram() {
        return contaInstagram;
    }

    public String getCnpj() {
        return cnpj;
    }
}
