package med.voll.api.paciente;

import aj.org.objectweb.asm.commons.Remapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Paciente - é o tipo da entidade que esse repository vai trabalhar
    // Long - é tipo da chave primária  do atributo
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);
}
