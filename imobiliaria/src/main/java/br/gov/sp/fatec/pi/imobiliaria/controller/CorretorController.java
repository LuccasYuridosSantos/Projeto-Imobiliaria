package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.vo.request.CorretorRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.response.CorretorResponse;
import br.gov.sp.fatec.pi.imobiliaria.service.CorretorService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/corretores")
public class CorretorController {

  private final CorretorService corretorService;

  public CorretorController(final CorretorService corretorService) {
    this.corretorService = corretorService;
  }

  @GetMapping
  public ResponseEntity<Page<CorretorResponse>> getAllCorretores(final Pageable pageable) {
    final var corretores = corretorService.getAllCorretores(pageable);
    return new ResponseEntity<>(corretores, HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CorretorResponse> getCorretorById(@PathVariable Long id) {
    final var response =corretorService.getCorretorById(id);
    return response.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<CorretorResponse> createCorretor(@RequestBody @Valid CorretorRequest corretorRequest) {
    final var response = corretorService.createCorretor(corretorRequest);
    return Optional.ofNullable(response).map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PutMapping("/{id}")
  public ResponseEntity<CorretorResponse> updateCorretor(@PathVariable Long id, @RequestBody @Valid CorretorRequest corretor) {
    final var response = corretorService.updateCorretor(id, corretor);
    return response.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCorretor(@PathVariable Long id) {
    corretorService.deleteCorretor(id);
    return ResponseEntity.noContent().build();
  }
}
