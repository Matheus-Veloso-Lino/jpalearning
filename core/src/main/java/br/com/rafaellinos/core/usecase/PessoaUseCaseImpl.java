package br.com.rafaellinos.core.usecase;

import br.com.rafaellinos.core.domain.*;
import br.com.rafaellinos.core.repository.*;
import br.com.rafaellinos.core.specification.*;
import lombok.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@RequiredArgsConstructor
public class PessoaUseCaseImpl implements PessoaUseCase {

    private final PessoaRepository pessoaRepository;

    @Override
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public PageableDomain<Pessoa> getPessoa(PessoaSpecification spec) {
        return pessoaRepository.get(spec);
    }

    @Override
    public Pessoa update(UUID id, Pessoa pessoa) {
        return patch(id, pessoa);
    }

    @Override
    public void delete(java.util.UUID id) {
        PessoaSpecification spec = PessoaSpecification.builder()
                .withId(id)
                .withPageNumber(0)
                .withPageSize(1)
                .build();

        PageableDomain<Pessoa> resultado = pessoaRepository.get(spec);

        if (resultado.getContent().isEmpty()) {
            throw new RuntimeException("Pessoa não encontrada");
        }
        pessoaRepository.deleteById(id);
    }

    @Override
    public Pessoa patch(java.util.UUID id, Pessoa pessoa) {
        PessoaSpecification spec = PessoaSpecification.builder()
                .withId(id)
                .withPageNumber(0)
                .withPageSize(1)
                .build();
        PageableDomain<Pessoa> resultado = pessoaRepository.get(spec);

        if (resultado.getContent().isEmpty()) {
            throw new RuntimeException("Pessoa não encontrada");
        }


        Pessoa pessoaExistente = resultado.getContent().get(0);

        //Atualiza só o que veio
        if (pessoa.getNome() != null) {
            pessoaExistente.setNome(pessoa.getNome());
        }
        if (pessoa.getSobrenome() != null) {
            pessoaExistente.setSobrenome(pessoa.getSobrenome());
        }
        if (pessoa.getDocumento() != null) {
            pessoaExistente.setDocumento(pessoa.getDocumento());
        }
        if (pessoa.getEmails() != null) {
            pessoaExistente.setEmails(pessoa.getEmails());
        }
        if (pessoa.getTelefones() != null) {
            pessoaExistente.setTelefones(pessoa.getTelefones());
        }
        if (pessoa.getEnderecos() != null) {
            pessoaExistente.setEnderecos(pessoa.getEnderecos());
        }
        return pessoaRepository.save(pessoaExistente);
    }
}