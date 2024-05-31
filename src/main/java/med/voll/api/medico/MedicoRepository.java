package med.voll.api.medico;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    // Medico - é o tipo da entidade que esse repository vai trabalhar
    // Long - é tipo da chave primária  do atributo

    Page<Medico> findAllByAtivoTrue(Pageable paginacao);
}
