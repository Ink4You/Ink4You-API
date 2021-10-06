package br.com.bandtec.ink4yousembanco.repository;

import br.com.bandtec.ink4yousembanco.model.Usuario;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmailAndSenha(String email, String senha);
}



