package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.vo.request.AgendamentoRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.response.AgendamentoResponse;
import br.gov.sp.fatec.pi.imobiliaria.service.AgendamentoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import jakarta.validation.*;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

  private final AgendamentoService service;

  public AgendamentoController(final AgendamentoService service) {
    this.service = service;
  }

  @GetMapping()
  public ResponseEntity<Page<AgendamentoResponse>> listarAgendamentos(final Pageable pageable) {
    return new ResponseEntity<>(service.listarAgendamentos(pageable), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AgendamentoResponse> buscarAgendamentos(@PathVariable final Long id) {
    final var response = service.buscarAgendamentoPorId(id);
    return response.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @GetMapping("/datahora")
  public ResponseEntity<List<AgendamentoResponse>> buscarAgendamentos(@RequestParam final LocalDateTime dataHora) {
    return new ResponseEntity<>(service.buscarAgendamentos(dataHora), HttpStatus.OK);
  }

  @PostMapping("/")
  public ResponseEntity<AgendamentoResponse> criarAgendamento(@RequestBody @Valid final AgendamentoRequest request) {
    return new ResponseEntity<>(service.salvarAgendamento(request), HttpStatus.CREATED);
  }


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
