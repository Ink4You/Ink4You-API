package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Estilo;
import br.com.bandtec.ink4yousembanco.model.EstiloTatuador;
import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.repository.EstiloRepository;
import br.com.bandtec.ink4yousembanco.repository.EstiloTatuadorRepository;
import br.com.bandtec.ink4yousembanco.repository.TatuadorRepository;
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

@SpringBootTest(classes = {EstiloTatuadorController.class, EstiloTatuadorRepository.class})
class EstiloTatuadorControllerTest {


    @Autowired
    EstiloTatuadorController controller;

    @MockBean
    EstiloTatuadorRepository repository;

    @MockBean
    EstiloRepository repositoryEstilo;

    @MockBean
    TatuadorRepository repositoryTatuador;



    // Teste de busca de todos os estilos de Tatuadores retornando 200
    @Test
    void getDeveRetornarStatus200ComCorpo_seHaEstilos() {

        List<EstiloTatuador> listaEstiloTatuadoresMock = List.of(mock(EstiloTatuador.class), mock(EstiloTatuador.class));
        when(repository.findAll()).thenReturn(listaEstiloTatuadoresMock);

        ResponseEntity resposta = controller.findAllEstiloTatuador();

        assertEquals(200, resposta.getStatusCodeValue());

        assertEquals(listaEstiloTatuadoresMock, resposta.getBody());
    }



    // Teste de busca de todos os estilos de Tatuadores retornando 204
    @Test
    void getDeveRetornarStatus204SemCorpo_seSemEstilos() {

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity resposta = controller.findAllEstiloTatuador();

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }



    // Teste de delete de Estilo de Tatuador por ID - retornando 200
    @Test
    void deleteRetornarStatus200_seIdExiste() {

        Integer idTeste = 900;

        when(repository.existsById(idTeste)).thenReturn(true);

        ResponseEntity resposta = controller.deletaEstiloUsuario(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());

    }



    // Teste de delete de Estilo de Tatuador por ID - retornando 404
    @Test
    void deleteDeveRetornarStatus404SemCorpo_seIdNaoExiste() {

        Integer idTeste = 900;

        when(repository.existsById(idTeste)).thenReturn(false);

        ResponseEntity resposta = controller.deletaEstiloUsuario(idTeste);

        assertEquals(404, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

}