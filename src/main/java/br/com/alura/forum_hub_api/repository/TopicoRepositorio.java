package br.com.alura.forum_hub_api.repository;

import br.com.alura.forum_hub_api.entity.topico.Topico;
import br.com.alura.forum_hub_api.entity.topico.TopicoStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TopicoRepositorio extends JpaRepository<Topico, Long> {
    @Query("SELECT t FROM Topico t WHERE t.ativo = true")
    Page<Topico> findAllAtivos(Pageable pageable);

    @Modifying
    @Query("UPDATE Topico t SET t.ativo = false WHERE t.id = :id")
    void desativar(@Param("id") Long id);

    Page<Topico> findByUsuarioId(Long usuarioId, Pageable pageable);

    Page<Topico> findByCursoId(Long cursoId, Pageable pageable);

    Page<Topico> findByStatus(TopicoStatus status, Pageable pageable);

    Page<Topico> findByTituloContainingIgnoreCase(String titulo, Pageable pageable);

    boolean existByIdAndAtivoTrue(Long id);
}
