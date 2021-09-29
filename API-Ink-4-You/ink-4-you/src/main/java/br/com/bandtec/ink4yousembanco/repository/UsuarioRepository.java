package br.com.bandtec.ink4yousembanco.repository;

import br.com.bandtec.ink4yousembanco.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {}
