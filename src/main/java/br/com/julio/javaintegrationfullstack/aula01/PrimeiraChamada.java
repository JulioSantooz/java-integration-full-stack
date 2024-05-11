package br.com.julio.javaintegrationfullstack.aula01;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/primeira_chamada")
public class PrimeiraChamada {

    @GetMapping
    public ResponseEntity<String> primeiraChamada() {
        return ResponseEntity.ok("Primeira chamada com sucesso");
    }
}
