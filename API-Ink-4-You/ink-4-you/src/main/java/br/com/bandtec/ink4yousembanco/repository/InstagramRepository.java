package br.com.bandtec.ink4yousembanco.repository;

import br.com.bandtec.ink4yousembanco.model.Instagram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface InstagramRepository extends JpaRepository<Instagram,Integer> {
    @Modifying
    @Query("delete from Instagram i where i.id_tatuador = ?1")
    void deleteByIdTatuador(Integer id_tatuador);
}
