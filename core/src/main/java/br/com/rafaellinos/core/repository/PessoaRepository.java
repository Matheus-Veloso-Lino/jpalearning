package br.com.rafaellinos.core.repository;

import br.com.rafaellinos.core.domain.PageableDomain;
import br.com.rafaellinos.core.domain.Pessoa;
import br.com.rafaellinos.core.specification.PessoaSpecification;

public interface PessoaRepository {

    Pessoa save(Pessoa pessoa);

    PageableDomain<Pessoa> get(PessoaSpecification spec);

    void deleteById(java.util.UUID id);
}
