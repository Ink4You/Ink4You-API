package br.com.bandtec.ink4you2;

public interface Autenticavel {

    public void cadastrarUsuario();

    public void logar(String email, String senha);

    public void logoff(Integer userID);
}
