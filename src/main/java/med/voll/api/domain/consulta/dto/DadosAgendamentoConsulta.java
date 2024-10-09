package med.voll.api.domain.consulta.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidade;

import java.time.LocalDateTime;

public record DadosAgendamentoConsulta(
        Long idMedico,
        // Anotação do BeanValidation
        @NotNull // Não pode ser nulo
        Long idPaciente,

        @NotNull // Não pode ser nulo
        @Future // Deve ser uma data futura
        //@JsonFormat(pattern = "dd/MM/yyyy HH:00:00")
        LocalDateTime data,
        Especialidade especialidade) {

}

