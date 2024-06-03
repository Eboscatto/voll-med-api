package med.voll.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import med.voll.api.domain.usuario.Usuario;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.Date;

@Service
public class TokenService {
    @Value("${api.security.token.secret}") // Faz a injeção de dependência da porpriedade declarada no application.properties
    private String secret;

    // Gera o token
    public String gerarToken(Usuario usuario) {

        try {
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Voll.med")
                    .withSubject(usuario.getLogin())
                    .withExpiresAt(dataExpiracao())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerar token", exception);
        }
    }
/*
    // Faz a validação do token
    public String getSubject(String tokenJWT) {
        try {
            var algoritmo = Algorithm.HMAC256("123456");
            return JWT.require(algoritmo)
                    .withIssuer("API Voll.med")
                    .build()
                    .verify(tokenJWT)
                    .getSubject();


        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token JWT inválido ou expirado!!");
        }
    }


 */

    // Gera período de validade do token
    private Instant dataExpiracao() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
