package com.example.docker.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collation = "pessoa")
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Pessoa implements Serializable {

    private static final long serialVersionUID = 7591279533401801918L;

    @EqualsAndHashCode.Include
    private String id;
    private String nome;
    private Integer idade;
    private Integer idadeMental;
}
