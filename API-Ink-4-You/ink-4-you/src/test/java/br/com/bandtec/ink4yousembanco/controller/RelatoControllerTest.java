package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Relato;
import br.com.bandtec.ink4yousembanco.model.Tatuagem;
import br.com.bandtec.ink4yousembanco.repository.RelatoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {RelatoController.class, RelatoRepository.class})
class RelatoControllerTest {

    @Autowired
    RelatoController controller;

    @MockBean
    RelatoRepository repository;


    // Teste de delete de Relato se ID existir - retornando 200
    @Test
    void deleteRetornarStatus200_seIdExiste() {

        Integer idTeste = 900;

        when(repository.existsById(idTeste)).thenReturn(true);

        ResponseEntity resposta = controller.deleteRelato(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());

    }



    // Teste de delete de Relato se ID NÃO existir - retornando 404
    @Test
    void deleteDeveRetornarStatus404SemCorpo_seIdNaoExiste() {

        Integer idTeste = 900;

        when(repository.existsById(idTeste)).thenReturn(false);

        ResponseEntity resposta = controller.deleteRelato(idTeste);

        assertEquals(404, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }



    // Teste de busca de 6 Relatos retornando 200
    @Test
    void getDeveRetornarStatus200ComCorpo_seHaRelatos() {

        List<Relato> listaRelatosMock = List.of(mock(Relato.class), mock(Relato.class));
        when(repository.findAll()).thenReturn(listaRelatosMock);

        ResponseEntity resposta = controller.buscarRelato();

        assertEquals(200, resposta.getStatusCodeValue());

        assertEquals(listaRelatosMock, resposta.getBody());
    }



    // Teste de busca de Relatos retornando 204
    @Test
    void getDeveRetornarStatus204SemCorpo_seSemRelatos() {

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity resposta = controller.buscarRelato();

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }
}