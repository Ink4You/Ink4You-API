package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Instagram;
import br.com.bandtec.ink4yousembanco.model.Relato;
import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.repository.InstagramRepository;
import br.com.bandtec.ink4yousembanco.repository.TatuadorRepository;
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

@SpringBootTest(classes = {InstagramController.class, InstagramRepository.class})
class InstagramControllerTest {

    @Autowired
    InstagramController controller;

    @MockBean
    InstagramRepository repository;

    @MockBean
    TatuadorRepository repositoryTatuador;



    // Teste de busca de imagens do Instagram dos Tatuadores retornando 200
    @Test
    void getDeveRetornarStatus200ComCorpo_seHaImagens() {

        List<Instagram> listaInstagramMock = List.of(mock(Instagram.class), mock(Instagram.class));
        when(repository.findAll()).thenReturn(listaInstagramMock);

        ResponseEntity resposta = controller.getImages();

        assertEquals(200, resposta.getStatusCodeValue());

        assertEquals(listaInstagramMock, resposta.getBody());
    }



    // Teste de busca de imagens do Instagram dos Tatuadores retornando 204
    @Test
    void getDeveRetornarStatus204SemCorpo_seSemImagens() {

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity resposta = controller.getImages();

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

}