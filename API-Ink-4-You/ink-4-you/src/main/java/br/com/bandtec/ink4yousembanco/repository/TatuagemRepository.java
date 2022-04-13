package br.com.bandtec.ink4yousembanco.repository;

import br.com.bandtec.ink4yousembanco.model.Tatuagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TatuagemRepository extends JpaRepository<Tatuagem, Integer> {
    @Query("select new br.com.bandtec.ink4yousembanco.model.Tatuagem(t.id_tatuagem, t.titulo, t.local_tatuagem, t.descricao, t.src_imagem, t.id_tatuador) from Tatuagem t where t.id_tatuador = ?1")
    List<Tatuagem> findByIdTatuador(Integer id);
    @Query(nativeQuery = true,
            value = "SELECT TOP 10 * from Tatuagem")
    List<Tatuagem> findLimit(Integer quantidade);
}