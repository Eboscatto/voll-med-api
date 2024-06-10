package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Stream;


@RestController
@RequestMapping("consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired // Injeta a classe AngendaDeConsultas
    private AgendaDeConsultas agenda;

    @Autowired
    private ConsultaRepository consultaRepository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DadosAgendamentoConsulta dados){

        var dto = agenda.agendar(dados);

        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DadosCancelamentoConsulta dados) {
        agenda.cancelar(dados);
        return ResponseEntity.noContent().build();
    }


    // Lista consultas ordenadas por data

    @GetMapping
    public Stream<DadosListagemConsulta> listar(@PageableDefault(size = 10, sort = {"data"}) Pageable paginacao) {

        return consultaRepository.findByMotivoCancelamentoIsNull(paginacao).stream().map(DadosListagemConsulta::new);
    }

}

