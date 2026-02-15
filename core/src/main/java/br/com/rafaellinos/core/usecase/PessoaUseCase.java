package br.com.rafaellinos.core.usecase;

import br.com.rafaellinos.core.domain.*;
import br.com.rafaellinos.core.specification.*;

import java.util.*;

public interface PessoaUseCase {

    Pessoa save(Pessoa pessoa);

    PageableDomain<Pessoa> getPessoa(PessoaSpecification spec);

    Pessoa patch(UUID id, Pessoa pessoa );

    void delete(UUID id);

    Pessoa update(UUID id, Pessoa pessoa);
}
