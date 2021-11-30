package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.model.Usuario;
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

@SpringBootTest(classes = {TatuadorController.class, TatuadorRepository.class})
class TatuadorControllerTest {

    @Autowired
    TatuadorController controller;

    @MockBean
    TatuadorRepository repository;


    // Teste de busca de todos os Tatuadores retornando 200
    @Test
    void getDeveRetornarStatus200ComCorpo_seHaTatuadores() {

        List<Tatuador> listaTatuadoresMock = List.of(mock(Tatuador.class), mock(Tatuador.class));
        when(repository.findAll()).thenReturn(listaTatuadoresMock);

        ResponseEntity resposta = controller.findAllTatuadores();

        assertEquals(200, resposta.getStatusCodeValue());

        assertEquals(listaTatuadoresMock, resposta.getBody());
    }



    // Teste de busca de todos os Tatuadores retornando 204
    @Test
    void getDeveRetornarStatus204SemCorpo_seSemTatuadores() {

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity resposta = controller.findAllTatuadores();

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }



    // Buscando Tatuador por id - se existe retorna 200 com corpo
    @Test
    void getIdDeveRetornarStatus200ComCorpo_seIdExiste() {

        Integer idTeste = 900;
        Tatuador tatuador = mock(Tatuador.class);

        when(repository.findById(idTeste)).thenReturn(Optional.of(tatuador));

        ResponseEntity resposta = controller.findByIdTatuador(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());

        assertEquals(tatuador, resposta.getBody());
    }



    // Buscando Tatuador por id - se NÃO existe retorna 404 sem corpo
    @Test
    void getFotoDeveRetornarStatus404SemCorpo_seIdNaoExiste() {

        Integer idTeste = 900;

        when(repository.existsById(idTeste)).thenReturn(false);

        ResponseEntity resposta = controller.findByIdTatuador(idTeste);

        assertEquals(404, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

}