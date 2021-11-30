package br.com.bandtec.ink4yousembanco.uteis;

import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.model.Usuario;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class TxtAdapter {

        // Método para fazer o download do Txt dos tatuadores (Exportação de arquivo)
        public static void downloadTxtTatuador(PrintWriter writer, ListaObj<Tatuador> tatuadores) {

            for (int i = 0; i < tatuadores.getTamanho(); i++){
                Tatuador tatuadorTxt  = tatuadores.getElemento(i);
                writer.write("ID: " + tatuadorTxt.getId_tatuador() + "\n" +
                        "Nome: " + tatuadorTxt.getNome() + "\n" +
                        "UserName: " + tatuadorTxt.getUsername() + "\n" +
                        "Nascimento: " + tatuadorTxt.getData_nascimento() + "\n" +
                        "CNPJ: " + tatuadorTxt.getCnpj() + "\n" +
                        "CEP: " + tatuadorTxt.getCep() + "\n" +
                        "Logradouro: " + tatuadorTxt.getLogradouro() + "\n" +
                        "Num Logradouro: " + tatuadorTxt.getNumero_logradouro() + "\n" +
                        "Telefone: " + tatuadorTxt.getTelefone() + "\n" +
                        "E-mail: " + tatuadorTxt.getEmail() + "\n" +
                        "Instagram: " + tatuadorTxt.getConta_instagram() + "\n \n");
            }
        }


        // Método para gravar registros para o import de arquivo
        public static void gravaRegistro(String registro, String nomeArq) {
            BufferedWriter saida = null;    // objeto usado para gravar no arquivo

            // Abre o arquivo
            try {
                // Abre o arquivo com append = true, para poder ir acrescentando registros no arquivo
                saida = new BufferedWriter(new FileWriter(nomeArq, true));
            } catch (IOException erro) {
                System.out.println("Erro ao abrir o arquivo: " + erro.getMessage());
            }

            // Grava o registro e fecha o arquivo
            try {
                saida.append(registro + "\n");  // Grava o registro e o final de registro (\n)
                saida.close();                  // Fecha o arquivo
            } catch (IOException erro) {
                System.out.println("Erro ao gravar no arquivo: " + erro.getMessage());
            }
        }
}
