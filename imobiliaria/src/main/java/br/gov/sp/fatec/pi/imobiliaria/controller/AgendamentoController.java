package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.request.AgendamentoRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.response.AgendamentoResponse;
import br.gov.sp.fatec.pi.imobiliaria.service.AgendamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Classe controller responsável por lidar com as requisições relacionadas a agendamentos.
 * Define os endpoints para listar, buscar, criar e excluir agendamentos.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

  private final AgendamentoService service;

  /**
   * Construtor da classe AgendamentoController.
   *
   * @param service O serviço responsável pela lógica de negócio dos agendamentos
   */
  public AgendamentoController(final AgendamentoService service) {
    this.service = service;
  }

  /**
   * Endpoint para listar os agendamentos.
   *
   * @param pageable O objeto Pageable para paginação dos resultados
   * @return Uma ResponseEntity contendo a lista de agendamentos e o status HTTP 200 OK
   */
  @GetMapping()
  public ResponseEntity<Page<AgendamentoResponse>> listarAgendamentos(final Pageable pageable) {
    return new ResponseEntity<>(service.listarAgendamentos(pageable), HttpStatus.OK);
  }

  /**
   * Endpoint para buscar um agendamento pelo seu ID.
   *
   * @param id O ID do agendamento a ser buscado
   * @return Uma ResponseEntity contendo o agendamento encontrado e o status HTTP 200 OK,
   *         ou o status HTTP 404 Not Found caso não seja encontrado um agendamento com o ID fornecido
   */
  @GetMapping("/{id}")
  public ResponseEntity<AgendamentoResponse> buscarAgendamentos(@PathVariable final Long id) {
    final var response = service.buscarAgendamentoPorId(id);
    return response.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
            .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Endpoint para buscar agendamentos por data e hora.
   *
   * @param dataHora A data e hora dos agendamentos a serem buscados
   * @return Uma ResponseEntity contendo a lista de agendamentos encontrados e o status HTTP 200 OK
   */
  @GetMapping("/datahora")
  public ResponseEntity<List<AgendamentoResponse>> buscarAgendamentos(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) final LocalDateTime dataHora) {
    return new ResponseEntity<>(service.buscarAgendamentos(dataHora), HttpStatus.OK);
  }

  /**
   * Endpoint para criar um novo agendamento.
   *
   * @param request O objeto AgendamentoRequest contendo os dados do agendamento a ser criado
   * @return Uma ResponseEntity contendo o agendamento criado e o status HTTP 201 Created
   */
  @PostMapping("/")
  public ResponseEntity<AgendamentoResponse> criarAgendamento(@RequestBody @Valid final AgendamentoRequest request) {
    return new ResponseEntity<>(service.salvarAgendamento(request), HttpStatus.CREATED);
  }

  /**
   * Endpoint para excluir um agendamento pelo seu ID.
   *
   * @param id O ID do agendamento a ser excluído
   * @return Uma ResponseEntity vazia com o status HTTP 204 No Content caso o agendamento seja excluído com sucesso,
   *         ou o status HTTP 404 Not Found caso não seja encontrado um agendamento com o ID fornecido
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<AgendamentoResponse> excluirAgendamento(@PathVariable final Long id) {
    final var clienteExistente = service.buscarAgendamentoPorId(id);
    if (clienteExistente.isPresent()) {
      service.excluirAgendamento(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}
