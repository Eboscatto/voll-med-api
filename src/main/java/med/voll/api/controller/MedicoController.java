package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {
    @Autowired  // Faz a injeção de dependências
    private MedicoRepository repository;

    // Cadastra médico
    @PostMapping
    @Transactional // Ativa a transação com o banco de dados
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var medico = new Medico(dados);
        repository.save(medico);

        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedico(medico));
    }

    /*
    // Lista todos os médicos ordenados por nome
    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        return repository.findAll(paginacao).map(DadosListagemMedico::new);
    }
   */

    // Lista médicos ativos ordenados por nome
    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
        return ResponseEntity.ok(page);

    }

    // Atualiza os dados do médico
    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
       var medico = repository.getReferenceById(dados.id()); // Carrega o objeto do banco de dados
        medico.atualizarInformacoes(dados); // Cria um construtor na Classe Medico
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }

    // Exclusão física de médico
    /*
    @DeleteMapping("/{id}") // Parâmetro dinâmico vindo da URL
    @Transactional
    public void excluir(@PathVariable Long id) {
        repository.deleteById(id);
    }
   */

    // Exclusão lógica de médico
    @DeleteMapping("/{id}") // Parâmetro dinâmico vindo da URL
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluir();
        return ResponseEntity.noContent().build();
    }



    // Detalhamneto de médico
    @GetMapping("/{id}") // Parâmetro dinâmico vindo da URL
    public ResponseEntity detalhar(@PathVariable Long id) {
        var medico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoMedico(medico));
    }
}
