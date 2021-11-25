package br.com.bandtec.ink4yousembanco.uteis;

import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.model.Usuario;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CsvAdapter {


//        public static void downloadCsvUsuario(PrintWriter writer, ListaObj<Usuario> user) {
//
//            writer.write("Nome;Data de nascimento;CPF;CEP;Telefone;Email \n");
//            for (int i = 0; i < user.getTamanho(); i++){
//                Usuario userCsv  = user.getElemento(i);
//                writer.format("%s;%s;%s;%s;%s;%s\n", userCsv.getNome(), userCsv.getData_nascimento(),
//                        userCsv.getCpf(), userCsv.getCep(), userCsv.getTelefone(),
//                        userCsv.getEmail());
//            }
//        }
//
//        public static void downloadCsvTatuador(PrintWriter writer, ListaObj<Tatuador> tatuadores) {
//
//            writer.write("Nome;UserName;Data de nascimento;CNPJ;CEP;Logradouro;Numero Logradouro;" +
//                    "Telefone;Email;Instagram \n");
//            for (int i = 0; i < tatuadores.getTamanho(); i++){
//                Tatuador tatuadorCsv  = tatuadores.getElemento(i);
//                writer.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n", tatuadorCsv.getNome(), tatuadorCsv.getUsername(),
//                        tatuadorCsv.getData_nascimento(),tatuadorCsv.getCnpj(), tatuadorCsv.getCep(),
//                        tatuadorCsv.getLogradouro(), tatuadorCsv.getNumero_logradouro(),
//                        tatuadorCsv.getTelefone(), tatuadorCsv.getEmail(), tatuadorCsv.getConta_instagram());
//            }
//
//        }


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
