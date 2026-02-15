package br.com.rafaellinos.core.domain;

import com.fasterxml.jackson.annotation.*;

public class Cep {

    private final String cepString;

    @JsonCreator
    public Cep(String cep) {
        this.cepString = cep;
    }

    public static Cep fromString(final String cep) {
        return new Cep(cep);
    }

    @JsonValue
    @Override
    public String toString() {
        return this.cepString;
    }
}
