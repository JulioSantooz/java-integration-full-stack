package br.com.julio.javaintegrationfullstack.models;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContratoEntrada {

    @NotBlank(message = "Informar o nome")
    private String nome;

    @NotBlank(message = "Informar o sobrenome")
    private String sobrenome;
}
