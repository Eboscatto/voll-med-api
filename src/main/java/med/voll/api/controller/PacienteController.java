package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.Medico;
import med.voll.api.paciente.DadosCadastroPaciente;
import med.voll.api.paciente.Paciente;
import med.voll.api.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    // Faz a injeção de dependências
    @Autowired
    private PacienteRepository repository;

    @PostMapping
    @Transactional // Ativa a transação com o banco de dados
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));// Criar um construtor na classe Paciente que recebe os DadosCadastroPaciente
    }
}
