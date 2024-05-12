package br.com.julio.javaintegrationfullstack.aula02.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column(name = "NOME_COMPLETO")
    private String nomeCompleto;

}
