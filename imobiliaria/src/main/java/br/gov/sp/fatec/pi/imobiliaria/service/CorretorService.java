package br.gov.sp.fatec.pi.imobiliaria.service;

import br.gov.sp.fatec.pi.imobiliaria.mappers.ModelMappers;
import br.gov.sp.fatec.pi.imobiliaria.model.Corretor;
import br.gov.sp.fatec.pi.imobiliaria.model.request.CorretorRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.response.CorretorResponse;
import br.gov.sp.fatec.pi.imobiliaria.repository.CorretorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe de serviço para manipulação de corretores.
 */
@Service
public class CorretorService {

  private CorretorRepository repository;
  private ModelMappers modelMappers;

  /**
   * Construtor da classe CorretorService.
   *
   * @param repository    Repositório de corretores
   * @param modelMappers  Mappers de modelos
   */
  public CorretorService(final CorretorRepository repository, final ModelMappers modelMappers) {
    this.repository = repository;
    this.modelMappers = modelMappers;
  }

  /**
   * Exclui um corretor com o ID especificado.
   *
   * @param id  ID do corretor a ser excluído
   */
  public void deleteCorretor(final Long id) {
    repository.deleteById(id);
  }

  /**
   * Atualiza um corretor existente com o ID e os dados fornecidos.
   *
   * @param id       ID do corretor a ser atualizado
   * @param request  Dados do corretor a serem atualizados
   * @return         Corretor atualizado (no formato de resposta)
   */
  public Optional<CorretorResponse> updateCorretor(final Long id, final CorretorRequest request) {
    final var corretor = repository.findById(id);
    if (corretor.isPresent()) {
      final var corretorAtualizado = Corretor.builder()
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

  /**
   * Retorna um corretor com o ID especificado no formato de resposta.
   *
   * @param id  ID do corretor
   * @return    Corretor encontrado (opcional)
   */
  public Optional<CorretorResponse> getCorretorById(final Long id) {
    return modelMappers.mapOptionalCorretor(repository.findById(id));
  }

  /**
   * Cria um novo corretor com base nos dados fornecidos.
   *
   * @param corretorRequest  Dados do corretor a ser criado
   * @return                 Corretor criado (no formato de resposta)
   */
  public CorretorResponse createCorretor(final CorretorRequest corretorRequest) {
    final var corretor = modelMappers.map(corretorRequest);
    return modelMappers.map(repository.save(corretor));
  }

  /**
   * Retorna uma página de corretores no formato de resposta.
   *
   * @param pageable  Configurações da página
   * @return          Página de corretores
   */
  public Page<CorretorResponse> getAllCorretores(final Pageable pageable) {
    return repository.findAll(pageable).map(modelMappers::map);
  }
}
