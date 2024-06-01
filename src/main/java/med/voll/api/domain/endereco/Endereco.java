package med.voll.api.domain.endereco;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// Anotações Lombok
@Embeddable
@Getter              // gera os métodos Getters
@NoArgsConstructor   // gera o construtor default(sem argumentos) exigência da JPA
@AllArgsConstructor  // gera o construtor que recebe totos os campos como argumento

// Entidade JPA
public class Endereco {
    @NotBlank
    private String logradouro;
    @NotBlank
    private String numero;
    @NotBlank
    private String bairro;
    @NotBlank
    @Pattern(regexp = "\\d{8}") // Expressão regular - o cep contém 8 digítos
    private String cep;
    private String complemento;
    @NotBlank
    private String cidade;
    @NotBlank
    private String uf;

    // Contrutor que recebe os dados DTO
    public Endereco(DadosEndereco dados) {
        this.logradouro = dados.logradouro();
        this.numero = dados.numero();
        this.bairro = dados.bairro();
        this.cep = dados.cep();
        this.cidade = dados.cidade();
        this.complemento = dados.complemento();
        this.uf = dados.uf();
    }

    // Construtor que recebe os dados a serem atualizados vindos da classe Medico
    public void atualizarInformacoes(DadosEndereco dados) {
        if (dados.logradouro() != null) {
            this.logradouro = dados.logradouro();
        }

        if (dados.numero() != null) {
            this.numero = dados.numero();
        }

        if (dados.bairro() != null) {
            this.bairro = dados.bairro();
        }

        if (dados.cep() != null) {
            this.cep = dados.cep();
        }

        if (dados.cidade() != null) {
            this.cidade = dados.cidade();
        }

        if (dados.complemento() != null) {
            this.complemento = dados.complemento();
        }

        if (dados.uf() != null) {
            this.uf = dados.uf();
        }
    }
}
