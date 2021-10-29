package br.com.bandtec.ink4yousembanco.uteis;

public class InstagramAdapter {

    public InstagramAdapter() {
    }

    public String changeNickname(String instagramNickname){
        if(instagramNickname.startsWith("@")){
            return instagramNickname;
        }
        return String.format("@%s", instagramNickname);
    }
}
