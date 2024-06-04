package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsultas;
import med.voll.api.domain.exception.ValidacaoException;
import med.voll.api.domain.medico.MedicoRepository;

public class ValidadorDeMedicoAtivo {

    private MedicoRepository medicoRepository;

    public void validar(DadosAgendamentoConsultas dados) {
        // Escolha do médico opcional
        if (dados.idMedico() == null) {
            return;
        }

        var medicoEstaAtivo = medicoRepository.findAtivoById(dados.idMedico());
        if (!medicoEstaAtivo) {
            throw new ValidacaoException("Consulta não pode ser agendada com o médico inativo!");
        }

    }
}
