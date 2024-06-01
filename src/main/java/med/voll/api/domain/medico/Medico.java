package med.voll.api.domain.medico;

import jakarta.persistence.*;
import lombok.*;
import med.voll.api.domain.endereco.Endereco;

// Anotações da biblioteca Lombok
@Getter                         // gera os métodos Getters
@Setter                         // gera os métodos Setters
@NoArgsConstructor              // gera o construtor default(sem argumentos) exigência da JPA
@AllArgsConstructor             // gera o construtor que recebe totos os campos como argumento
@EqualsAndHashCode(of = "id")   // gera o EqualsHashCode em cima do id e não de todos os atributos

// Anotações da biblioteca JPA
@Entity(name = "medico")
@Table(name="medicos")

// Classe JPA
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String telefone;
    private String crm;
    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    //Embeddable Attribute considera no banco de dados os campos da classe enderço fazem
    // parte da mesma tabela de médicos
    @Embedded
    private Endereco endereco;
    private Boolean ativo;

    public Medico(DadosCadastroMedico dados) {
        this.ativo = true;
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.endereco = new Endereco(dados.endereco()); // Cria um construtor na classe Endereco que recebe os DadosEndereco
        this.especialidade = dados.especialidade();

    }
    // Construtor que recebe os dados a serem atualizados, vindos do método AtualizarInformacoes
    public void atualizarInformacoes(DadosAtualizacaoMedico dados) {
        if (dados.nome() != null) {
            this.nome = dados.nome();
        }
        if (dados.telefone() != null) {
            this.telefone = dados.telefone();
        }
        if (dados.endereco() != null) {
            this.endereco.atualizarInformacoes(dados.endereco()); //Cria um construtor na classe Endereco
        }
    }
    // Inativa o médico
    public void excluir() {
       this.ativo = false;
    }

}
