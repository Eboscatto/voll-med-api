package med.voll.api.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

// Faz o acesso a tabela de usuários
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Faz a consulta do usário no bando de dados
    UserDetails findByLogin(String login);
}
