package med.voll.api.domain.usuario;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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
public class Usuario implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String senha;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
