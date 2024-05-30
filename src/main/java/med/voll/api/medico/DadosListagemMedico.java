package med.voll.api.medico;

// Rebe apenas os dados necessários a listagem de médicos
public record DadosListagemMedico(String nome, String email, String crm, Especialidade especialidade) {
    public DadosListagemMedico(Medico medico) {
        this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());

    }
}
