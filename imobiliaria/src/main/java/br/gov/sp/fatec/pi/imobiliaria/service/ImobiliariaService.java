package br.gov.sp.fatec.pi.imobiliaria.service;

import br.gov.sp.fatec.pi.imobiliaria.exception.ImobiliariaException;
import br.gov.sp.fatec.pi.imobiliaria.mappers.ModelMappers;
import br.gov.sp.fatec.pi.imobiliaria.model.Corretor;
import br.gov.sp.fatec.pi.imobiliaria.model.Imobiliaria;
import br.gov.sp.fatec.pi.imobiliaria.model.request.EnderecoRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.request.ImobiliariaPutRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.request.ImobiliariaRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.response.ImobiliariaResponse;
import br.gov.sp.fatec.pi.imobiliaria.repository.CorretorRepository;
import br.gov.sp.fatec.pi.imobiliaria.repository.ImobiliariaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe de serviço para manipulação de imobiliárias.
 */
@Service
public class ImobiliariaService {

  private final ImobiliariaRepository repository;
  private final CorretorRepository corretorRepository;
  private final ModelMappers modelMappers;

  /**
   * Construtor da classe ImobiliariaService.
   *
   * @param repository         Repositório de imobiliárias
   * @param corretorRepository Repositório de corretores
   * @param modelMappers       Mappers de modelos
   */
  public ImobiliariaService(final ImobiliariaRepository repository, final CorretorRepository corretorRepository,
                            final ModelMappers modelMappers) {
    this.repository = repository;
    this.corretorRepository = corretorRepository;
    this.modelMappers = modelMappers;
  }

  /**
   * Retorna uma página de imobiliárias no formato de resposta.
   *
   * @param pageable  Configurações da página
   * @return          Página de imobiliárias
   */
  public Page<ImobiliariaResponse> getAllImobiliarias(final Pageable pageable) {
    return repository.findAll(pageable).map(modelMappers::map);
  }

  /**
   * Retorna uma imobiliária com o ID especificado no formato de resposta.
   *
   * @param id  ID da imobiliária
   * @return    Imobiliária encontrada (opcional)
   */
  public Optional<ImobiliariaResponse> getImobiliariaById(final Long id) {
    return repository.findById(id).map(modelMappers::map);
  }

  /**
   * Cria uma nova imobiliária com base nos dados fornecidos.
   *
   * @param request  Dados da imobiliária a ser criada
   * @return         Imobiliária criada (no formato de resposta)
   */
  public ImobiliariaResponse createImobiliaria(final ImobiliariaRequest request) {
    final var imobiliaria = repository.save(modelMappers.map(request));
    return modelMappers.map(imobiliaria);
  }

  /**
   * Adiciona um corretor à imobiliária especificada.
   *
   * @param codImobiliaria  ID da imobiliária
   * @param codCorretor     ID do corretor
   * @return                Imobiliária atualizada (no formato de resposta)
   * @throws ImobiliariaException se a imobiliária ou o corretor não forem encontrados
   */
  public ImobiliariaResponse adicionarCorretor(final Long codImobiliaria, final Long codCorretor) throws ImobiliariaException {
    Imobiliaria imobiliaria = repository.findById(codImobiliaria)
            .orElseThrow(() -> ImobiliariaException.imobiliariaNaoEncontrada(codImobiliaria));

    Corretor corretor = corretorRepository.findById(codCorretor)
            .orElseThrow(() -> ImobiliariaException.corretorNaoEncontrado(codCorretor));

    List<Corretor> corretores = imobiliaria.getCorretores();
    if (ObjectUtils.isEmpty(corretores)) {
      corretores = new ArrayList<>();
      imobiliaria.setCorretores(corretores);
    }
    corretores.add(corretor);

    Imobiliaria savedImobiliaria = repository.save(imobiliaria);
    return modelMappers.map(savedImobiliaria);
  }

  /**
   * Atualiza uma imobiliária existente com base nos dados fornecidos.
   *
   * @param id       ID da imobiliária a ser atualizada
   * @param request  Dados da imobiliária a serem atualizados
   * @return         Imobiliária atualizada (no formato de resposta)
   * @throws ImobiliariaException se a imobiliária não for encontrada
   */
  public ImobiliariaResponse updateImobiliaria(Long id, ImobiliariaPutRequest request) throws ImobiliariaException {
    Imobiliaria existingImobiliaria = repository.findById(id)
            .orElseThrow(() -> ImobiliariaException.imobiliariaNaoEncontrada(id));

    updateImobiliariaRoot(existingImobiliaria, request);
    updateEnderecoImobiliaria(existingImobiliaria, request.getEndereco());

    repository.save(existingImobiliaria);
    return modelMappers.map(existingImobiliaria);
  }

  /**
   * Exclui uma imobiliária com o ID especificado.
   *
   * @param id  ID da imobiliária a ser excluída
   */
  public void deleteImobiliaria(final Long id) {
    repository.deleteById(id);
  }

  private void updateImobiliariaRoot(final Imobiliaria existingImobiliaria, final ImobiliariaPutRequest request) {
    if (request.getNome() != null) {
      existingImobiliaria.setNome(request.getNome());
    }

    if (request.getTelefone() != null) {
      existingImobiliaria.setTelefone(request.getTelefone());
    }

    if (request.getEmail() != null) {
      existingImobiliaria.setEmail(request.getEmail());
    }
  }

  private void updateEnderecoImobiliaria(final Imobiliaria imobiliaria, final EnderecoRequest enderecoRequest) {
    final var enderecoExistente = imobiliaria.getEndereco();
    if (enderecoRequest != null) {
      if (enderecoExistente == null) {
        imobiliaria.setEndereco(modelMappers.map(enderecoRequest));
        return;
      }

      if (enderecoRequest.getRua() != null) {
        enderecoExistente.setRua(enderecoRequest.getRua());
      }
      if (enderecoRequest.getNumero() != null) {
        enderecoExistente.setNumero(enderecoRequest.getNumero());
      }
      if (enderecoRequest.getComplemento() != null) {
        enderecoExistente.setComplemento(enderecoRequest.getComplemento());
      }
      if (enderecoRequest.getBairro() != null) {
        enderecoExistente.setBairro(enderecoRequest.getBairro());
      }
      if (enderecoRequest.getCidade() != null) {
        enderecoExistente.setCidade(enderecoRequest.getCidade());
      }
      if (enderecoRequest.getEstado() != null) {
        enderecoExistente.setEstado(enderecoRequest.getEstado());
      }
      if (enderecoRequest.getCep() != null) {
        enderecoExistente.setCep(enderecoRequest.getCep());
      }

    }

  }
}

