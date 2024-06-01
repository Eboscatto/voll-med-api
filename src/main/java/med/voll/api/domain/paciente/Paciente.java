package med.voll.api.domain.paciente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.domain.endereco.Endereco;

// Anotações da biblioteca Lombok
@Getter                         // gera os métodos Getters
@NoArgsConstructor              // gera o construtor default(sem argumentos) exigência da JPA
@AllArgsConstructor             // gera o construtor que recebe totos os campos como argumento
@EqualsAndHashCode(of = "id")   // gera o EqualsHashCode em cima do id e não de todos os atributos

// Anotações da biblioteca JPA
@Entity(name = "paciente")
@Table(name = "pacientes")

// Entidade JPA
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String cpf;

    @Embedded
    private Endereco endereco;
    private Boolean ativo ;

    public Paciente(DadosCadastroPaciente dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco()); // Construtor já criado na classe Endereco recebendo os DadosEndereco
    }

    // Construtor que recebe os dados a serem atualizados, vindos do método AtualizarInformacoes
    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco()); // Construtor já criado na classe Endereco
        }
    }

    public void excluir() {
        this.ativo = false;
    }
}

