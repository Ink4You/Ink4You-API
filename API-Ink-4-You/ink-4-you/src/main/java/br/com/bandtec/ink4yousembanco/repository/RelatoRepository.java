package br.com.bandtec.ink4yousembanco.repository;

import br.com.bandtec.ink4yousembanco.model.Relato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelatoRepository extends JpaRepository<Relato, Integer> {
}
