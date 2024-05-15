package br.com.julio.javaintegrationfullstack.services;

import br.com.julio.javaintegrationfullstack.models.ContratoEntrada;
import br.com.julio.javaintegrationfullstack.models.ContratoSaida;
import br.com.julio.javaintegrationfullstack.models.Usuario;
import br.com.julio.javaintegrationfullstack.models.UsuarioRepositorio;
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
import java.util.Optional;

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

        return ResponseEntity.ok().body(usuarioRepositorio.save(usuario));
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> retornaNomeCompleto(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        if(usuario.isPresent()) {
            return ResponseEntity.ok().body(usuario.get());
        } else {
            return ResponseEntity.status(204).build();
        }
    }

    @GetMapping("/todos")
    @ResponseBody
    public ResponseEntity<List<Usuario>> retornaTodosNomesCompletos() {
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        if(usuarios.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.ok().body(usuarios);
        }
    }

    @PatchMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> atualizarSobrenome(@PathVariable Long id,
                                                      @RequestParam String sobrenome) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        if(usuario.isPresent()) {
            String nome = usuario.get().getNomeCompleto().split(" ")[0];
            usuario.get().setNomeCompleto(nome + " " + sobrenome);

            return ResponseEntity.ok().body(usuarioRepositorio.save(usuario.get()));
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> atualizarEntidade(@PathVariable Long id,
                                                      @Valid @RequestBody ContratoEntrada contratoEntrada) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        if(usuario.isPresent()) {
            usuario.get().setNomeCompleto(contratoEntrada.getNome() + " " + contratoEntrada.getSobrenome());

            return ResponseEntity.ok().body(usuarioRepositorio.save(usuario.get()));
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity<Usuario> atualizarEntidade(@PathVariable Long id) {
        Optional<Usuario> usuario = usuarioRepositorio.findById(id);
        if(usuario.isPresent()) {
            usuarioRepositorio.delete((usuario.get()));
            return ResponseEntity.accepted().build();
        } else {
            return ResponseEntity.unprocessableEntity().build();
        }
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