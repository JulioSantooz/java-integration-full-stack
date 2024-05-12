package br.com.julio.javaintegrationfullstack.aula02;

import br.com.julio.javaintegrationfullstack.aula02.models.ContratoEntrada;
import br.com.julio.javaintegrationfullstack.aula02.models.ContratoSaida;
import br.com.julio.javaintegrationfullstack.aula02.models.Usuario;
import br.com.julio.javaintegrationfullstack.aula02.models.UsuarioRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/primeiras-chamadas")
public class PrimeirasChamadas {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @GetMapping("/saudacao")
    @ResponseBody
    public ResponseEntity<String> primeiraChamada() {
        return ResponseEntity.ok("Olá! :)");
    }

    @PostMapping("/nome_completo")
    @ResponseBody
    public ResponseEntity<ContratoSaida> primeiraChamadaPOST(@Valid @RequestBody ContratoEntrada contratoEntrada) {
        ContratoSaida saida = new ContratoSaida(contratoEntrada.getNome(), contratoEntrada.getSobrenome());

        return ResponseEntity.ok().body(saida);
    }

    @PostMapping("/salva_nome_completo")
    @ResponseBody
    public ResponseEntity<Usuario> salvaNomeCompleto(@Valid @RequestBody ContratoEntrada contratoEntrada) {
        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(contratoEntrada.getNome() + " " + contratoEntrada.getSobrenome());

        return ResponseEntity.ok().body(
                usuarioRepositorio.save(usuario)
        );
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> retornaNomeCompleto(@PathVariable Long id) {
        return ResponseEntity.ok().body(usuarioRepositorio.findById(id).get());
    }

    @GetMapping("/todos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> retornaTodosNomesCompletos() {
        return ResponseEntity.ok().body(usuarioRepositorio.findAll());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

}
