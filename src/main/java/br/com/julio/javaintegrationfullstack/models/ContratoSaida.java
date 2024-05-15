package br.com.julio.javaintegrationfullstack.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContratoSaida {
    private String nomeCompleto;

    public ContratoSaida(String nome, String sobrenome) {
        this.nomeCompleto = nome + " " + sobrenome;
    }
}
