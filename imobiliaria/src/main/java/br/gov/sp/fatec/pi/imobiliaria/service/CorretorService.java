package br.gov.sp.fatec.pi.imobiliaria.service;

import br.gov.sp.fatec.pi.imobiliaria.mappers.ModelMappers;
import br.gov.sp.fatec.pi.imobiliaria.model.Corretor;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.request.CorretorRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.response.CorretorResponse;
import br.gov.sp.fatec.pi.imobiliaria.repository.CorretorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CorretorService {

  private CorretorRepository repository;
  private ModelMappers modelMappers;

  public CorretorService(final CorretorRepository repository, final ModelMappers modelMappers) {
    this.repository = repository;
    this.modelMappers = modelMappers;
  }

  public void deleteCorretor(final Long id) {
    repository.deleteById(id);
  }

  public Optional<CorretorResponse> updateCorretor(final Long id, final CorretorRequest request) {
    final var corretor = repository.findById(id);
    if(corretor.isPresent()){
      final var corretorAtualizado = Corretor
          .builder()
          .id(corretor.get().getId())
          .email(request.getEmail())
          .nome(request.getNome())
          .telefone(request.getTelefone())
          .imobiliaria(corretor.get().getImobiliaria())
          .build();
      return Optional.ofNullable(modelMappers.map(repository.save(corretorAtualizado)));
    }
    return Optional.empty();
  }

  public Optional<CorretorResponse> getCorretorById(final Long id) {
    return  modelMappers.mapOptionalCorretor(repository.findById(id));
  }

  public CorretorResponse createCorretor(final CorretorRequest corretorRequest) {
    final var corretor = modelMappers.map(corretorRequest);
    return modelMappers.map(repository.save(corretor));
  }

  public Page<CorretorResponse> getAllCorretores(final Pageable pageable) {
    return repository.findAll(pageable).map(modelMappers::map);
  }
}
