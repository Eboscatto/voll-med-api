package med.voll.api.endereco;

// DTO Endereco
public record DadosEndereco(String logradouro,
                            String cep,
                            String numero,
                            String complemento,
                            String cidade,
                            String bairro,
                            String uf) {
}
