package br.com.bandtec.ink4yousembanco.repository;

import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.model.Tatuagem;
import br.com.bandtec.ink4yousembanco.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TatuadorRepository extends JpaRepository<Tatuador, Integer> {
    Tatuador findByEmailAndSenha(String email, String senha);
    @Query(nativeQuery = true,
            value = "SELECT TOP 10 * from tatuador order by id_tatuador desc")
    List<Tatuador> findLast();
}
