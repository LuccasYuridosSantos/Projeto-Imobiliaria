package br.gov.sp.fatec.pi.imobiliaria.service;

import br.gov.sp.fatec.pi.imobiliaria.exception.UsuarioException;
import br.gov.sp.fatec.pi.imobiliaria.mappers.ModelMappers;
import br.gov.sp.fatec.pi.imobiliaria.model.Usuario;
import br.gov.sp.fatec.pi.imobiliaria.model.request.UsuarioRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.response.UsuarioResponse;
import br.gov.sp.fatec.pi.imobiliaria.repository.ClienteRepository;
import br.gov.sp.fatec.pi.imobiliaria.repository.ImobiliariaRepository;
import br.gov.sp.fatec.pi.imobiliaria.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

/**
 * Classe de serviço para manipulação de usuários.
 */
@Service
public class UsuarioService {

  private final UsuarioRepository repository;
  private final ClienteRepository clienteRepository;
  private final ImobiliariaRepository imobiliariaRepository;
  private final ModelMappers mappers;

  /**
   * Construtor da classe UsuarioService.
   *
   * @param repository             Repositório de usuários
   * @param clienteRepository      Repositório de clientes
   * @param imobiliariaRepository  Repositório de imobiliárias
   * @param mappers                Mappers de modelos
   */
  public UsuarioService(final UsuarioRepository repository, final ClienteRepository clienteRepository, final ImobiliariaRepository imobiliariaRepository, final ModelMappers mappers) {
    this.repository = repository;
    this.clienteRepository = clienteRepository;
    this.imobiliariaRepository = imobiliariaRepository;
    this.mappers = mappers;
  }

  /**
   * Retorna uma página de usuários no formato de resposta.
   *
   * @param pageable  Configurações da página
   * @return          Página de usuários
   */
  public Page<UsuarioResponse> listarClientes(final Pageable pageable) {
    final var usuarioPage = repository.findAll(pageable);
    return usuarioPage.map(mappers::map);
  }

  /**
   * Retorna um usuário com o ID especificado no formato de resposta.
   *
   * @param id  ID do usuário
   * @return    Usuário encontrado (opcional)
   */
  public Optional<UsuarioResponse> getUsuarioById(final Long id) {
    return mappers.mapOptionalUsuario(repository.findById(id));
  }

  /**
   * Cria um novo usuário com base nos dados fornecidos.
   *
   * @param usuarioRequest  Dados do usuário a ser criado
   * @return                Usuário criado (no formato de resposta)
   * @throws UsuarioException se o nome de usuário já estiver em uso
   */
  public UsuarioResponse createUsuario(final UsuarioRequest usuarioRequest) throws UsuarioException {
    final var user = repository.findUsuarioByUsernameIgnoreCase(usuarioRequest.getUsername());
    if (user.isPresent()) {
      throw UsuarioException.usuarioExistente(usuarioRequest.getUsername());
    }
    final var usuario = Usuario.builder()
            .name(usuarioRequest.getName())
            .password(passwordEncoder().encode(usuarioRequest.getPassword()))
            .username(usuarioRequest.getUsername())
            .authorities(Optional.ofNullable(usuarioRequest.getAuthorities()).orElse("ROLE_USER"))
            .build();

    setEntities(usuarioRequest, usuario);

    final var result = repository.save(usuario);

    return mappers.map(result);
  }

  private void setEntities(final UsuarioRequest usuarioRequest, final Usuario usuario) {
    if (!isEmpty(usuarioRequest.getCliente())) {
      usuario.setCliente(clienteRepository.save(mappers.map(usuarioRequest.getCliente())));
    }
    if (!isEmpty(usuarioRequest.getImobiliaria())) {
      usuario.setImobiliaria(imobiliariaRepository.save(mappers.map(usuarioRequest.getImobiliaria())));
    }
  }

  /**
   * Atualiza um usuário existente com o ID e os dados fornecidos.
   *
   * @param id              ID do usuário a ser atualizado
   * @param usuarioRequest  Dados do usuário a serem atualizados
   * @return                Usuário atualizado (no formato de resposta)
   * @throws UsuarioException se o nome de usuário já estiver em uso
   */
  public UsuarioResponse updateUsuario(final Long id, final UsuarioRequest usuarioRequest) throws UsuarioException {
    final var usuario = repository.findById(id).orElseThrow(() -> UsuarioException.usuarioNaoEncontrado(id));
    final var checkUsername = repository.findUsuarioByUsernameIgnoreCase(usuarioRequest.getUsername());
    if (checkUsername.isPresent()) {
      throw UsuarioException.usuarioExistente(usuarioRequest.getUsername());
    }
    Optional.ofNullable(usuarioRequest.getName()).ifPresent(usuario::setName);
    Optional.ofNullable(usuarioRequest.getUsername()).ifPresent(usuario::setUsername);
    Optional.ofNullable(usuarioRequest.getPassword()).ifPresent(password -> usuario.setPassword(usuarioRequest.getPassword()));
    Optional.ofNullable(usuarioRequest.getAuthorities()).ifPresent(usuario::setAuthorities);
    return mappers.map(repository.save(usuario));
  }

  /**
   * Exclui um usuário com o ID especificado.
   *
   * @param id  ID do usuário a ser excluído
   */
  public void deleteUsuarioComId(final Long id) {
    repository.deleteById(id);
  }

  /**
   * Retorna o codificador de senha utilizado pelo serviço.
   *
   * @return  Codificador de senha
   */
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
  
  
   /**
    * Realiza o login de um usuário.
    *
    * @param login O objeto contendo os dados de login do usuário.
    * @return Um Optional contendo o objeto de resposta com informações do usuário logado, se o login for bem-sucedido.
    *         Caso contrário, retorna um Optional vazio.
   */
   public Optional<UsuarioResponse> logar(final UsuarioRequest login){
        var encoder =  passwordEncoder();
        Optional<Usuario> usuario = repository.findByUsername(login.getUsername());

        if(funcionario.isPresent()){
            if (encoder.matches(login.getPassword(),usuario.get().getPassword())) {

                String auth = login.getUsername()+":"+login.getPassword();
                byte[] encodeAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
                String authHeader = "Basic "+new String(encodeAuth);
                final var response = mappers.map(usuario);
                response.setToken(authHeader);

                return Optional.ofNullable(response):
            }
        }

        return Optional.empty();
    }
}
