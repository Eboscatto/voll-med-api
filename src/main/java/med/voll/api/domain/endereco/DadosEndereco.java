package med.voll.api.domain.endereco;

// DTO Endereco
public record DadosEndereco(String logradouro,
                            String cep,
                            String numero,
                            String complemento,
                            String cidade,
                            String bairro,
                            String uf) {
}
