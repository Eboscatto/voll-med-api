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

    // Cadastra médico
    @PostMapping
    @Transactional // Ativa a transação com o banco de dados
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados) {
        repository.save(new Medico(dados)); // Criar um construtor na classe Medico que recebe os DadosCadastroMedico
    }
    /*
    // Lista médicos ordenados por nome
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }
   */

    // Lista médicos ativos ordenados por nome
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    // Atualiza os dados do médico
    @PutMapping
    @Transactional
    public void atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
       var medico = repository.getReferenceById(dados.id()); // Carrega o objeto do banco de dados
        medico.atualizarInformacoes(dados); // Cria um construtor na Classe Medico
    }

    // Exclui médico
    /*
    @DeleteMapping("/{id}") // Parâmetro dinâmico vindo da URL
    @Transactional
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
   */

    @DeleteMapping("/{id}") // Parâmetro dinâmico vindo da URL
    @Transactional
    public void excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }
}
