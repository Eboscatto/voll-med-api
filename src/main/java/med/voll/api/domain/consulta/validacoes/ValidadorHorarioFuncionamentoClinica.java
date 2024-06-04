package med.voll.api.domain.consulta.validacoes;

import med.voll.api.domain.consulta.DadosAgendamentoConsultas;
import med.voll.api.domain.exception.ValidacaoException;

import java.time.DayOfWeek;

public class ValidadorHorarioFuncionamentoClinica {


    // Válida o horário da consulta
    public void validar(DadosAgendamentoConsultas dados) {
        var dataConsulta = dados.data();
        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var antesDoHorario = dataConsulta.getHour() < 7;
        var depoisDoHorario = dataConsulta.getHour() > 18;

        if (domingo || antesDoHorario || depoisDoHorario) {
            throw new ValidacaoException("Consulta fora do hoário de funcionamento da clínica!");
        }
    }
}
