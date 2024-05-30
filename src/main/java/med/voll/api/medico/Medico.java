package med.voll.api.medico;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.endereco.Endereco;

// Anotações da biblioteca JPA
@Entity(name = "medico")
@Table(name="medicos")
// Anotações da biblioteca Lombok
@Getter                         // gera os métodos Getters
@NoArgsConstructor              // gera o construtor default(sem argumentos) exigência da JPA
@AllArgsConstructor             // gera o construtor que recebe totos os campos como argumento
@EqualsAndHashCode(of = "id")   // gera o EqualsHashCode em cima do id e não de todos os atributos

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

    public Medico(DadosCadastroMedico dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.crm = dados.crm();
        this.endereco = new Endereco(dados.endereco()); // Cria um construtor na classe Endereco que recebe os DadosEndereco
        this.especialidade = dados.especialidade();

    }
}
