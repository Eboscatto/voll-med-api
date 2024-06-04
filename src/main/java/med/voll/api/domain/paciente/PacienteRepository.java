package med.voll.api.domain.paciente;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {

    // Paciente - é o tipo da entidade que esse repository vai trabalhar
    // Long - é tipo da chave primária  do atributo
    Page<Paciente> findAllByAtivoTrue(Pageable paginacao);



    // Pegar somente o atributo "ativo" da  entidade "Paciente"
    @Query("""
            select p.ativo
            from Paciente P
            where
            p.id = :id
            """)
    Boolean findByAtivoId(Long idPaciente);
}
