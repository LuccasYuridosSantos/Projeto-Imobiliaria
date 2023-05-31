package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.request.CorretorRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.response.CorretorResponse;
import br.gov.sp.fatec.pi.imobiliaria.service.CorretorService;
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
 * A classe CorretorController representa o controlador REST para as operações relacionadas à entidade Corretor.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/corretores")
public class CorretorController {

  private final CorretorService corretorService;

  /**
   * Construtor da classe CorretorController.
   *
   * @param corretorService O serviço responsável pela lógica de negócio dos corretores
   */
  public CorretorController(final CorretorService corretorService) {
    this.corretorService = corretorService;
  }

  /**
   * Endpoint para listar todos os corretores.
   *
   * @param pageable O objeto Pageable para paginação dos resultados
   * @return Uma ResponseEntity contendo a lista de corretores e o status HTTP 200 OK
   */
  @GetMapping
  public ResponseEntity<Page<CorretorResponse>> getAllCorretores(final Pageable pageable) {
    final var corretores = corretorService.getAllCorretores(pageable);
    return new ResponseEntity<>(corretores, HttpStatus.OK);
  }

  /**
   * Endpoint para buscar um corretor pelo seu ID.
   *
   * @param id O ID do corretor a ser buscado
   * @return Uma ResponseEntity contendo o corretor encontrado e o status HTTP 200 OK,
   *         ou o status HTTP 404 Not Found caso não seja encontrado um corretor com o ID fornecido
   */
  @GetMapping("/{id}")
  public ResponseEntity<CorretorResponse> getCorretorById(@PathVariable Long id) {
    final var response = corretorService.getCorretorById(id);
    return response.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Endpoint para criar um novo corretor.
   *
   * @param corretorRequest O objeto CorretorRequest contendo os dados do corretor a ser criado
   * @return Uma ResponseEntity contendo o corretor criado e o status HTTP 200 OK,
   *         ou o status HTTP 404 Not Found caso não seja possível criar o corretor
   */
  @PostMapping
  public ResponseEntity<CorretorResponse> createCorretor(@RequestBody @Valid CorretorRequest corretorRequest) {
    final var response = corretorService.createCorretor(corretorRequest);
    return Optional.ofNullable(response)
            .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Endpoint para atualizar os dados de um corretor.
   *
   * @param id       O ID do corretor a ser atualizado
   * @param corretor O objeto CorretorRequest contendo os novos dados do corretor
   * @return Uma ResponseEntity contendo o corretor atualizado e o status HTTP 200 OK,
   *         ou o status HTTP 404 Not Found caso não seja possível atualizar o corretor
   */
  @PutMapping("/{id}")
  public ResponseEntity<CorretorResponse> updateCorretor(@PathVariable Long id, @RequestBody @Valid CorretorRequest corretor) {
    final var response = corretorService.updateCorretor(id, corretor);
    return response.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Endpoint para excluir um corretor.
   *
   * @param id O ID do corretor a ser excluído
   * @return Uma ResponseEntity vazia com o status HTTP 204 No Content
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCorretor(@PathVariable Long id) {
    corretorService.deleteCorretor(id);
    return ResponseEntity.noContent().build();
  }
}
