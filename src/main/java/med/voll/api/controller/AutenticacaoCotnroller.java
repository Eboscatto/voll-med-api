package med.voll.api.controller;

import med.voll.api.domain.usuario.dadosAutenticacao;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoCotnroller {

    @Autowired // Faz injeçao de dependência do objeto AuthenticationManager
    private AuthenticationManager manager; // Deve ser criado o método AuthenticationManager na Classe SecurityConfigurations
    @PostMapping
    public ResponseEntity efetuarLoin(@RequestBody @Validated dadosAutenticacao dados) {
        var token = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); // Converte meu DTO para o DTO do Spring
        var authentication = manager.authenticate(token); // Devolve um objeto que recebe o usuario autenticado no sistema
        return ResponseEntity.ok().build();

    }
}
