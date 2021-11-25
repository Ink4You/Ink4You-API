package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.repository.TatuadorRepository;
import br.com.bandtec.ink4yousembanco.uteis.CsvAdapter;
import br.com.bandtec.ink4yousembanco.uteis.FilaObj;
import br.com.bandtec.ink4yousembanco.uteis.ListaObj;
import br.com.bandtec.ink4yousembanco.uteis.ResponseWebScraper;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/tatuadores")
public class TatuadorController {

    @Autowired
    private TatuadorRepository repositoryTatuador;

    // métodos do CRUD de Tatuador aqui:

    // Endpoint de busca de Tatuadores (Todos)
    @GetMapping
    public ResponseEntity findTatuadores(){
        List<Tatuador> tatuadores = repositoryTatuador.findAll();
        LocalDate now = LocalDate.now();
        if (tatuadores != null){
            for (int i = 0; i < tatuadores.size(); i++){

                LocalDate nascimento =  tatuadores.get(i).getData_nascimento();

                if ((nascimento != null)){
                    int years = Period.between(nascimento, now).getYears();
                    tatuadores.get(i).setIdade(years);
                }
            }
            return ResponseEntity.status(200).body(tatuadores);
        }
        return ResponseEntity.status(401).build();
    }


    // Endpoint de busca de Tatuador por ID
    @GetMapping(path = {"/{id}"})
    public ResponseEntity findByIdTatuador(@PathVariable Integer id){
        return repositoryTatuador.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    // POST de cadastro de Tatuador
    @PostMapping("/cadastro-tatuador")
    public ResponseEntity cadastroTatuador(@RequestBody Tatuador tatuador){
        repositoryTatuador.save(tatuador);
        return ResponseEntity.status(201).build();
    }

    // EndPoint de alteração de dados do Tatuador
    @PutMapping(value="/{id}")
    public ResponseEntity updateTatuador(@PathVariable("id") Integer id,
                                 @RequestBody Tatuador tatuador) {
        return repositoryTatuador.findById(id)
                .map(record -> {
                    record.setNome(tatuador.getNome());
                    record.setUsername(tatuador.getUsername());
                    record.setData_nascimento(tatuador.getData_nascimento());
                    record.setCnpj(tatuador.getCnpj());
                    record.setCep(tatuador.getCep());
                    record.setLogradouro(tatuador.getLogradouro());
                    record.setNumero_logradouro(tatuador.getNumero_logradouro());
                    record.setTelefone(tatuador.getTelefone());
                    record.setEmail(tatuador.getEmail());
                    record.setSenha(tatuador.getSenha());
                    record.setConta_instagram(tatuador.getConta_instagram());
                    record.setFoto_perfil(tatuador.getFoto_perfil());
                    Tatuador updated = repositoryTatuador.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }


    // Endpoint para deletar o Tatuador por ID
    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> deleteTatuador(@PathVariable Integer id) {
        return repositoryTatuador.findById(id)
                .map(record -> {
                    repositoryTatuador.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }


    // Endpoint de login (autenticação) do tatuador
    @GetMapping("/login/{email}/{senha}")
    public ResponseEntity autenticacaoTatuador(@PathVariable String email, @PathVariable String senha){

        Tatuador autendicado =  repositoryTatuador.findByEmailAndSenha(email, senha);

        LocalDate nascimento = autendicado.getData_nascimento();
        LocalDate now = LocalDate.now();

        if (autendicado != null) {
            int years = Period.between(nascimento, now).getYears();
            autendicado.setIdade(years);
            return ResponseEntity.status(200).body(autendicado);
        }

        return ResponseEntity.status(401).build();

    }

    // Endpoint para gerar o relatorio em csv e fazer o download
//    @GetMapping("/relatorio-tatuadores.csv")
//    public void getCsvTatuador(HttpServletResponse response) throws IOException {
//
//        List<Tatuador> listaJava = repositoryTatuador.findAll();
//
//        ListaObj<Tatuador> lista = new ListaObj<>(listaJava.size());
//
//        for (int i = 0; i < repositoryTatuador.count(); i++) {
//            lista.adicionar(listaJava.get(i));
//        }
//
//        response.setContentType("text/csv");
//        CsvAdapter.downloadCsvTatuador(response.getWriter(), lista);
//        response.setStatus(200);
//
//    }


    @GetMapping("/conexao/instagram")
    public ResponseEntity getUsuariosInstagram(){
        List<String> contaInstagram = new ArrayList<>();

        List<Tatuador> tatuadores = repositoryTatuador.findAll();

        for(int i = 0; i < tatuadores.size(); i++){
            if(tatuadores.get(i).getConta_instagram() != null){
                contaInstagram.add(tatuadores.get(i).getConta_instagram());
            }
        }
        return ResponseEntity.ok().body(contaInstagram);
    }

    @GetMapping("/instagram/buscar-fotos/{account}")
    public ResponseEntity getInstagramImages(@PathVariable String account) {


        List<String> instagramImagesSrc = new ArrayList<>();
        ResponseWebScraper response = null;
        try {
            RestTemplate restTemplate = new RestTemplate();
            response = restTemplate.getForObject(String.format("http://localhost:3000/insta/%s",account), ResponseWebScraper.class);
        } catch(Exception err) {
            return ResponseEntity.status(503).build();
        }

        if(response.getData().isEmpty()) {
            return ResponseEntity.status(204).body(response);
        }

        return ResponseEntity.ok().body(response);

    }



    @GetMapping("/relatorio-tatuadores")
    public ResponseEntity gravaArquivoTxt() {

        List<Tatuador> tatuadors = repositoryTatuador.findAll();

        if (!tatuadors.isEmpty()){

            int contaRegDados = 0;      // contador de registros de dados (para poder gravar no trailer)

            // Monta o registro de header
            String header = "tatuadores";
            Date dataDeHoje = new Date();       // Data e hora do momento, no formato padrão do Java
            SimpleDateFormat formataData = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");     // configura o padrão de formatação da data e horário
            header += formataData.format(dataDeHoje);   // Formata a data e hora para o padrão desejado
            header += "01";

            // Grava o header
            CsvAdapter.gravaRegistro(header, "arquivo");


            for (Tatuador t : tatuadors){

                String corpo = "02";
                corpo += String.format("%03d",t.getId_tatuador());
                corpo += String.format("%-15.15s",t.getNome());
                corpo += String.format("%-15.15s",t.getUsername());
                corpo += String.format("%-15.15s",t.getData_nascimento());
                corpo += String.format("%-14.14s",t.getCnpj());
                corpo += String.format("%-10.10s",t.getCep());
                corpo += String.format("%-30.30s",t.getLogradouro());
                corpo += String.format("%-3.3s",t.getNumero_logradouro());
                corpo += String.format("-15.15s",t.getTelefone());
                corpo += String.format("%-30.30s",t.getEmail());
                corpo += String.format("%-15.15s",t.getConta_instagram());


                // Incrementa o contador de registro de dados
                contaRegDados++;
                // Grava o registro de corpo no arquivo
                CsvAdapter.gravaRegistro(corpo, "arquivo");

            }


            // Monta e grava o registro de trailer
            String trailer = "01";
            trailer += String.format("%010d", contaRegDados);   // contador de registros de dados
            CsvAdapter.gravaRegistro(trailer, "arquivo");

            return ResponseEntity.status(201).build();

        }

        return ResponseEntity.status(204).build();

    }
}
