package med.voll.api.domain.medico;

// Rebe apenas os dados necessários a listagem de médicos
public record DadosListagemMedico(Long id, String nome, String email,String telefone, String crm, Especialidade especialidade) {
    public DadosListagemMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getEmail(), medico.getTelefone(), medico.getCrm(), medico.getEspecialidade());

    }
}
