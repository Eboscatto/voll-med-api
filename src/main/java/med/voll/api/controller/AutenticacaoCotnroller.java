package med.voll.api.controller;

import med.voll.api.domain.usuario.Usuario;
import med.voll.api.domain.usuario.DadosAutenticacao;
import med.voll.api.infra.security.DadosTokenJWT;
import med.voll.api.infra.security.TokenService;
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

    // Dispara o processo de autenticação
    @Autowired // Faz injeçao de dependência do objeto AuthenticationManager
    private AuthenticationManager manager; // Deve ser criado o método AuthenticationManager na Classe SecurityConfigurations

    @Autowired
    private TokenService tokenService;
    @PostMapping
    public ResponseEntity efetuarLoin(@RequestBody @Validated DadosAutenticacao dados) {

        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha()); // Converte meu DTO para o DTO do Spring

        var authentication = manager.authenticate(authenticationToken); // Devolve um objeto que recebe o usuario autenticado no sistema

        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT)); // Devolve o token
    }
}
