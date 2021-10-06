package br.com.bandtec.ink4yousembanco.repository;

import br.com.bandtec.ink4yousembanco.model.Tatuador;
import br.com.bandtec.ink4yousembanco.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TatuadorRepository extends JpaRepository<Tatuador, Integer> {
    @Query("select t from Tatuador t where t.email = ?1 and t.senha = ?2")
    Tatuador findByEmailAndSenha(String email, String senha);
}
