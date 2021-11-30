package br.com.bandtec.ink4yousembanco.controller;

import br.com.bandtec.ink4yousembanco.model.Usuario;
import br.com.bandtec.ink4yousembanco.repository.UsuarioRepository;
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

@SpringBootTest(classes = {UsuarioController.class, UsuarioRepository.class})
class UsuarioControllerTest {

    @Autowired
    UsuarioController controller;

    @MockBean
    UsuarioRepository repository;


    // Teste de busca de todos os usuarios retornando 200
    @Test
    void getDeveRetornarStatus200ComCorpo_seHaUsuarios() {

        List<Usuario> listaUsuariosMock = List.of(mock(Usuario.class), mock(Usuario.class));
        when(repository.findAll()).thenReturn(listaUsuariosMock);

        ResponseEntity resposta = controller.findAllUsuarios();

        assertEquals(200, resposta.getStatusCodeValue());

        assertEquals(listaUsuariosMock, resposta.getBody());
    }



    // Teste de busca de todos os usuarios retornando 204
    @Test
    void getDeveRetornarStatus204SemCorpo_seSemUsuarios() {

        when(repository.findAll()).thenReturn(new ArrayList<>());

        ResponseEntity resposta = controller.findAllUsuarios();

        assertEquals(204, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }



    // Buscando usuario por id - se existe retorna 200 com corpo
    @Test
    void getIdDeveRetornarStatus200ComCorpo_seIdExiste() {

        Integer idTeste = 900;
        Usuario user = mock(Usuario.class);

        when(repository.findById(idTeste)).thenReturn(Optional.of(user));

        ResponseEntity resposta = controller.findByIdUsuario(idTeste);

        assertEquals(200, resposta.getStatusCodeValue());

        assertEquals(user, resposta.getBody());
    }



    // Buscando usuario por id - se NÃO existe retorna 404 sem corpo
    @Test
    void getFotoDeveRetornarStatus404SemCorpo_seIdNaoExiste() {

        Integer idTeste = 900;

        when(repository.existsById(idTeste)).thenReturn(false);

        ResponseEntity resposta = controller.findByIdUsuario(idTeste);

        assertEquals(404, resposta.getStatusCodeValue());

        assertNull(resposta.getBody());
    }

}