package br.com.bandtec.ink4yousembanco.repository;

import br.com.bandtec.ink4yousembanco.model.Instagram;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface InstagramRepository extends JpaRepository<Instagram,Integer> {
    @Modifying
    @Query("delete from Instagram i where i.id_tatuador = ?1")
    void deleteByIdTatuador(Integer id_tatuador);

    @Modifying
    @Query("select id, imagem, imagem_byte from Instagram i where i.id_tatuador = ?1")
    Object[] findImagemByIdTatuador(Integer id_tatuador);

    @Query( "select new br.com.bandtec.ink4yousembanco.model.Instagram(i.id, i.id_tatuador, i.imagem, i.imagem_byte) from Instagram i where i.id_tatuador = ?1")
    List<Instagram> findImagemByIdTatuadorJson(Integer id_tatuador);

}
