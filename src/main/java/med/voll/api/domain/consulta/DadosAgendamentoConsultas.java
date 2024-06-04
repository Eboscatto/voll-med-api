package med.voll.api.domain.consulta;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsultas(
        Long idMedico,

        // Anotação do BeanValidation
        @NotNull // Não pode ser nulo
        Long idPaciente,

        @NotNull // Não pode ser nulo
        @Future // Deve ser uma data futura
        LocalDateTime data,
        Especialidade especialidade) {

}

