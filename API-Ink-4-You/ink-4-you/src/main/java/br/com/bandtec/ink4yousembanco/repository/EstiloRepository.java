package br.com.bandtec.ink4yousembanco.repository;

import br.com.bandtec.ink4yousembanco.dto.CreateRequestEstiloDto;
import br.com.bandtec.ink4yousembanco.model.Estilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstiloRepository extends JpaRepository<Estilo, Integer> {
}
