package med.voll.api.paciente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Paciente - é o tipo da entidade que esse repository vai trabalhar
    // Long - é tipo da chave primária  do atributo

}
