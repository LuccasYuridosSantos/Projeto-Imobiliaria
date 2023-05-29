package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.vo.request.ImobiliariaPutRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.request.ImobiliariaRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.response.ImobiliariaResponse;
import br.gov.sp.fatec.pi.imobiliaria.service.ImobiliariaService;
import jakarta.validation.Valid;
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

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/imobiliaria")
public class ImobiliariaController {

  private final ImobiliariaService service;

  public ImobiliariaController(final ImobiliariaService service) {
    this.service = service;
  }

  @GetMapping
  public ResponseEntity<Page<ImobiliariaResponse>> getAllImobiliarias(Pageable pageable) {
    final var response = service.getAllImobiliarias(pageable);
    return ResponseEntity.ok(response);
  }

  @GetMapping("/{id}")
  public ResponseEntity<ImobiliariaResponse> getImobiliariaById(@PathVariable Long id) {
    final var response = service.getImobiliariaById(id);
    return response.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<ImobiliariaResponse> createImobiliaria(@RequestBody @Valid ImobiliariaRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(service.createImobiliaria(request));
  }

  @PutMapping("/{id}")
  public ResponseEntity<ImobiliariaResponse> updateImobiliaria(@PathVariable Long id, @RequestBody @Valid ImobiliariaPutRequest request) {
    return Optional.ofNullable(service.updateImobiliaria(id, request))
        .map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping("/{id}/corretor/{codCorretor}")
  public ResponseEntity<ImobiliariaResponse> adicionarCorretoAImobiliaria(@PathVariable Long id, @PathVariable Long codCorretor) {
    return Optional.ofNullable(service.adicionarCorretor(id,codCorretor))
        .map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteImobiliaria(@PathVariable Long id) {
    service.deleteImobiliaria(id);
    return ResponseEntity.noContent().build();
  }
}
