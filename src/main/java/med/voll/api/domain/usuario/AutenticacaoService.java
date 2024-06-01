package med.voll.api.domain.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// Faz o serviço de autenticaççao
@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired // Faz a injeção de dependência do usuarioRepository
    private UsuarioRepository usuarioRepository;

    // Spring chama esse método toda vez que um usuário fizer login
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByLogin(username); // Criar o método na interace UsuaioRepository
    }
}
