package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.*;

// Anotações da biblioteca Lombok
@Getter                         // gera os métodos Getters
@Setter                         // gera os métodos Setters
@NoArgsConstructor              // gera o construtor default(sem argumentos) exigência da JPA
@AllArgsConstructor             // gera o construtor que recebe totos os campos como argumento
@EqualsAndHashCode(of = "id")   // gera o EqualsHashCode em cima do id e não de todos os atributos

// Anotações da biblioteca JPA
@Entity(name = "usuario")
@Table(name="usuarios")

// Entidade JPA
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

}
