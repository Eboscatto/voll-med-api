package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;
import java.util.List;

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
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }

    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
       var medico = repository.getReferenceById(dados.id()); // Carrega o objeto no banco de dados
        medico.atualizarInformacoes(dados); // Cria um construtor na Classe Medico
    }
}
