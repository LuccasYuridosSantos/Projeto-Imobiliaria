package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.vo.request.UsuarioRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.response.UsuarioResponse;
import br.gov.sp.fatec.pi.imobiliaria.service.UsuarioService;
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
@RequestMapping("/usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public ResponseEntity<Page<UsuarioResponse>> getAllUsuarios(final Pageable pageable) {
    return new ResponseEntity<>(usuarioService.listarClientes(pageable), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable Long id) {
    final var usuario = usuarioService.getUsuarioById(id);
    return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping
  public ResponseEntity<UsuarioResponse> createUsuario(@RequestBody UsuarioRequest usuarioRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUsuario(usuarioRequest));
  }

  @PutMapping("/{id}")
  public ResponseEntity<UsuarioResponse> updateUsuario(@PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest) {
    final var usuario = usuarioService.updateUsuario(id, usuarioRequest);
    return Optional.ofNullable(usuario).map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
    usuarioService.deleteUsuarioComId(id);
    return ResponseEntity.noContent().build();
  }
}
