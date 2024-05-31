package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.medico.DadosAtualizacaoMedico;
import med.voll.api.medico.DadosListagemMedico;
import med.voll.api.medico.Medico;
import med.voll.api.paciente.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    // Faz a injeção de dependências
    @Autowired
    private PacienteRepository repository;

    // Cadastra paciente
    @PostMapping
    @Transactional // Ativa a transação com o banco de dados
    public void cadastrarPaciente(@RequestBody @Valid DadosCadastroPaciente dados) {
        repository.save(new Paciente(dados));// Criar um construtor na classe Paciente que recebe os DadosCadastroPaciente
    }
/*
    // Lista pacientes ordenados por nome
    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemPaciente::new);
    }
 */

    // Lista pacientes ativos ordenados por nome
    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemPaciente::new);
    }

    // Atualiza os dados do paciente
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados) {
        var paciente = repository.getReferenceById(dados.id()); // Carrega o objeto no banco de dados
        paciente.atualizarInformacoes(dados); // Cria um construtor na Classe Paciente
    }
/*
    // Exclui Paciente
    @DeleteMapping("/{id}")
    @Transactional
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
 */
    // Exclusão lógica de paciente
    @DeleteMapping("/{id}") // Parâmetro dinâmico vindo da URL
    @Transactional
    public void excluir(@PathVariable Long id) {
        var paciente = repository.getReferenceById(id);
        paciente.excluir();
    }
}
