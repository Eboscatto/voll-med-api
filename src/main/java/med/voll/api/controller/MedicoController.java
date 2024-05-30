package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosCadastroMedico;
import med.voll.api.medico.Medico;
import med.voll.api.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.beans.Transient;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired  // Faz a injeção de dependências
    private MedicoRepository repository;

    // Método que faz o cadastro do médico no bando de dados
    @PostMapping
    @Transactional // Ativa a transação com o banco de dados
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados)); // Criar um construtor na classe Medico que recebe os DadosCadastroMedico
    }
}
