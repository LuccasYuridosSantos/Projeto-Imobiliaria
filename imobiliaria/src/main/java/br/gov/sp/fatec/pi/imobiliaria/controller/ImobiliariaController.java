package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.request.ImobiliariaPutRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.request.ImobiliariaRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.response.ImobiliariaResponse;
import br.gov.sp.fatec.pi.imobiliaria.service.ImobiliariaService;
import javax.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * A classe ImobiliariaController representa o controlador REST para as operações relacionadas à entidade Imobiliaria.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/imobiliaria")
public class ImobiliariaController {

  private final ImobiliariaService service;

  /**
   * Construtor da classe ImobiliariaController.
   *
   * @param service O serviço responsável pela lógica de negócio das imobiliárias
   */
  public ImobiliariaController(final ImobiliariaService service) {
    this.service = service;
  }

  /**
   * Endpoint para listar todas as imobiliárias.
   *
   * @param pageable O objeto Pageable para paginação dos resultados
   * @return Uma ResponseEntity contendo a lista de imobiliárias e o status HTTP 200 OK
   */
  @GetMapping
  public ResponseEntity<Page<ImobiliariaResponse>> getAllImobiliarias(Pageable pageable) {
    final var response = service.getAllImobiliarias(pageable);
    return ResponseEntity.ok(response);
  }

  /**
   * Endpoint para buscar uma imobiliária pelo seu ID.
   *
   * @param id O ID da imobiliária a ser buscada
   * @return Uma ResponseEntity contendo a imobiliária encontrada e o status HTTP 200 OK,
   *         ou o status HTTP 404 Not Found caso não seja encontrada uma imobiliária com o ID fornecido
   */
  @GetMapping("/{id}")
  public ResponseEntity<ImobiliariaResponse> getImobiliariaById(@PathVariable Long id) {
    final var response = service.getImobiliariaById(id);
    return response.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Endpoint para criar uma nova imobiliária.
   *
   * @param request O objeto ImobiliariaRequest contendo os dados da imobiliária a ser criada
   * @return Uma ResponseEntity contendo a imobiliária criada e o status HTTP 201 Created
   */
  @PostMapping
  public ResponseEntity<ImobiliariaResponse> createImobiliaria(@RequestBody @Valid ImobiliariaRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createImobiliaria(request));
  }

  /**
   * Endpoint para atualizar os dados de uma imobiliária.
   *
   * @param id      O ID da imobiliária a ser atualizada
   * @param request O objeto ImobiliariaPutRequest contendo os novos dados da imobiliária
   * @return Uma ResponseEntity contendo a imobiliária atualizada e o status HTTP 200 OK,
   *         ou o status HTTP 404 Not Found caso não seja possível atualizar a imobiliária
   */
  @PutMapping("/{id}")
  public ResponseEntity<ImobiliariaResponse> updateImobiliaria(@PathVariable Long id, @RequestBody @Valid ImobiliariaPutRequest request) {
    return Optional.ofNullable(service.updateImobiliaria(id, request))
            .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Endpoint para adicionar um corretor à imobiliária.
   *
   * @param id          O ID da imobiliária
   * @param codCorretor O código do corretor a ser adicionado
   * @return Uma ResponseEntity contendo a imobiliária atualizada com o corretor adicionado e o status HTTP 200 OK,
   *         ou o status HTTP 404 Not Found caso não seja possível adicionar o corretor à imobiliária
   */
  @PutMapping("/{id}/corretor/{codCorretor}")
  public ResponseEntity<ImobiliariaResponse> adicionarCorretorAImobiliaria(@PathVariable Long id, @PathVariable Long codCorretor) {
    return Optional.ofNullable(service.adicionarCorretor(id, codCorretor))
            .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Endpoint para excluir uma imobiliária.
   *
   * @param id O ID da imobiliária a ser excluída
   * @return Uma ResponseEntity vazia com o status HTTP 204 No Content
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteImobiliaria(@PathVariable Long id) {
    service.deleteImobiliaria(id);
    return ResponseEntity.noContent().build();
  }
}
