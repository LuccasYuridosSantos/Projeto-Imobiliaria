package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.request.UsuarioRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.response.UsuarioResponse;
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

/**
 * Esta classe representa o controlador REST responsável por lidar com as operações relacionadas aos usuários.
 * Ela fornece endpoints para criar, buscar, atualizar e excluir usuários.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

  private final UsuarioService usuarioService;

  /**
   * Construtor da classe UsuarioController.
   *
   * @param usuarioService O serviço responsável pela lógica de negócio dos usuários
   */
  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  /**
   * Retorna uma lista paginada de todos os usuários.
   *
   * @param pageable O objeto Pageable para controlar a paginação dos resultados
   * @return Uma ResponseEntity contendo a lista de usuários e o status HTTP 200 OK
   */
  @GetMapping
  public ResponseEntity<Page<UsuarioResponse>> getAllUsuarios(final Pageable pageable) {
    return new ResponseEntity<>(usuarioService.listarClientes(pageable), HttpStatus.OK);
  }

  /**
   * Retorna um usuário pelo seu ID.
   *
   * @param id O ID do usuário a ser buscado
   * @return Uma ResponseEntity contendo o usuário encontrado e o status HTTP 200 OK,
   *         ou o status HTTP 404 Not Found caso o usuário não seja encontrado
   */
  @GetMapping("/{id}")
  public ResponseEntity<UsuarioResponse> getUsuarioById(@PathVariable Long id) {
    final var usuario = usuarioService.getUsuarioById(id);
    return usuario.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Cria um novo usuário.
   *
   * @param usuarioRequest O objeto UsuarioRequest contendo os dados do usuário a ser criado
   * @return Uma ResponseEntity contendo o usuário criado e o status HTTP 201 Created
   */
  @PostMapping
  public ResponseEntity<UsuarioResponse> createUsuario(@RequestBody UsuarioRequest usuarioRequest) {
    return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.createUsuario(usuarioRequest));
  }

  /**
   * Atualiza um usuário existente.
   *
   * @param id             O ID do usuário a ser atualizado
   * @param usuarioRequest O objeto UsuarioRequest contendo os novos dados do usuário
   * @return Uma ResponseEntity contendo o usuário atualizado e o status HTTP 200 OK,
   *         ou o status HTTP 404 Not Found caso o usuário não seja encontrado
   */
  @PutMapping("/{id}")
  public ResponseEntity<UsuarioResponse> updateUsuario(@PathVariable Long id, @RequestBody UsuarioRequest usuarioRequest) {
    final var usuario = usuarioService.updateUsuario(id, usuarioRequest);
    return Optional.ofNullable(usuario).map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  /**
   * Exclui um usuário pelo seu ID.
   *
   * @param id O ID do usuário a ser excluído
   * @return Uma ResponseEntity vazia com o status HTTP 204 No Content,
   *         ou o status HTTP 404 Not Found caso o usuário não seja encontrado
   */
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
    usuarioService.deleteUsuarioComId(id);
    return ResponseEntity.noContent().build();
  }
  
  /**
   * Realiza o login de um usuário.
   *
   * @param request O objeto contendo os dados de login do usuário.
   * @return Um ResponseEntity contendo o objeto de resposta com informações do usuário logado, se o login for bem-sucedido.
   *         Caso contrário, retorna um ResponseEntity com status HTTP UNAUTHORIZED (401).
   */
  @PostMapping("/logar")
  public ResponseEntity<UsuarioResponse> logarUsuario(@RequestBody final UsuarioRequest request){
    return usuarioService.logar(user).map(resp -> ResponseEntity.ok().body(resp))
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
  }    
}
