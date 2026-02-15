package br.com.rafaellinos.repository;

import br.com.rafaellinos.core.domain.*;
import br.com.rafaellinos.core.repository.*;
import br.com.rafaellinos.core.specification.*;
import br.com.rafaellinos.repository.entity.*;
import br.com.rafaellinos.repository.jparepository.*;
import br.com.rafaellinos.repository.jpaspecification.*;
import br.com.rafaellinos.repository.mapper.*;
import jakarta.transaction.*;
import lombok.*;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.*;
import org.springframework.stereotype.*;

import java.util.*;

@Component
@RequiredArgsConstructor
public class PessoaRepositoryImpl implements PessoaRepository {

    private final PessoaJpaRepository pessoaJpaRepository;
    private final PessoaEntityMapper mapper;

    @Override
    @Transactional
    public Pessoa save(Pessoa pessoa) {
        PessoaEntity pessoaEntity = mapper.toEntity(pessoa);
        PessoaQualificationEntity pessoaQualificationEntity = new PessoaQualificationEntity();
        pessoaQualificationEntity.setId((long) PersonQualification.UNKWON.getCodigo());
        pessoaEntity.setQualification(pessoaQualificationEntity);
        return mapper.toDomain(pessoaJpaRepository.save(pessoaEntity));
    }

    @Override
    @Transactional
    public PageableDomain<Pessoa> get(PessoaSpecification spec) {
        Specification<PessoaEntity> jpaSpec = PessoaJpaSpecification.from(spec);
        Pageable pageable = PageRequest.of(spec.getPageNumber(), spec.getPageSize());
        Page<PessoaEntity> pessoaEntities = pessoaJpaRepository
                .findAll(jpaSpec, pageable);
        PageableDomain<Pessoa> pessoaPageableDomain = new PageableDomain<>();
        pessoaPageableDomain.setContent(pessoaEntities.stream().map(mapper::toDomain).toList());
        pessoaPageableDomain.setTotalPages(pessoaEntities.getTotalPages());
        pessoaPageableDomain.setTotalElements(pessoaEntities.getNumberOfElements());
        return pessoaPageableDomain;
    }

    @Override
    @Transactional
    public void deleteById(UUID id) {
        pessoaJpaRepository.deleteById(id);
    }
}
