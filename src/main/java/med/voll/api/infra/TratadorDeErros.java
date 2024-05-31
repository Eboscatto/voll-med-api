package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Field;

@RestControllerAdvice // Informa ao Spring que ele deve carregar essa classe
public class TratadorDeErros {

    // Trata erro 404 tipo EntityNotFoundException
    @ExceptionHandler(EntityNotFoundException.class) // Informa ao Spring para qual exceção esse método será chamado
    public ResponseEntity tratarErro404() {
        return ResponseEntity.notFound().build();
    }

    // Tratar erro 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException ex) {
      var erros = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(erros.stream().map(DadosErroValidacaoDto::new).toList());
    }

    // Converte a lista de FieldErro para DadosErroValidação
    private record DadosErroValidacaoDto(String campo, String mensagem) {
        public DadosErroValidacaoDto(FieldError erro) {
            this(erro.getField(), erro.getDefaultMessage());
        }
    }
}
