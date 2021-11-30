package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.model.Tatuagem;
import br.com.bandtec.ink4yousembanco.repository.TatuagemRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {TatuagemController.class, TatuagemRepository.class})
class TatuagemControllerTest {

    @Autowired
    TatuagemController controller;

    @MockBean
    TatuagemRepository repository;



    // Teste de busca de todos as Tatuagens retornando 200
    @Test
    void getDeveRetornarStatus200ComCorpo_seHaTatuagens() {

        List<Tatuagem> listaTatuagensMock = List.of(mock(Tatuagem.class), mock(Tatuagem.class));
        when(repository.findAll()).thenReturn(listaTatuagensMock);

        ResponseEntity resposta = controller.findAllTatuagens();

        assertEquals(200, resposta.getStatusCodeValue());

        assertEquals(listaTatuagensMock, resposta.getBody());
    }



    // Teste de busca de todas as Tatuagens retornando 204
    @Test
    void getDeveRetornarStatus204SemCorpo_seSemTatuagens() {

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity resposta = controller.findAllTatuagens();

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }



    // Buscando Tatuagem por id - se existe retorna 200 com corpo
    @Test
    void getIdDeveRetornarStatus200ComCorpo_seIdExiste() {

        Integer idTeste = 900;
        Tatuagem tatuagem = mock(Tatuagem.class);

        when(repository.findById(idTeste)).thenReturn(Optional.of(tatuagem));

        ResponseEntity resposta = controller.findByIdTatuagem(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());

        assertEquals(tatuagem, resposta.getBody());
    }



    // Buscando Tatuagem por id - se NÃO existe retorna 404 sem corpo
    @Test
    void getFotoDeveRetornarStatus404SemCorpo_seIdNaoExiste() {

        Integer idTeste = 900;

        when(repository.existsById(idTeste)).thenReturn(false);

        ResponseEntity resposta = controller.findByIdTatuagem(idTeste);

        assertEquals(404, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

}