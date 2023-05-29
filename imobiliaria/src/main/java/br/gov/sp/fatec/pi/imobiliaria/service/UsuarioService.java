package br.gov.sp.fatec.pi.imobiliaria.service;

import br.gov.sp.fatec.pi.imobiliaria.exception.UsuarioException;
import br.gov.sp.fatec.pi.imobiliaria.mappers.ModelMappers;
import br.gov.sp.fatec.pi.imobiliaria.model.Usuario;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.request.UsuarioRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.response.UsuarioResponse;
import br.gov.sp.fatec.pi.imobiliaria.repository.ClienteRepository;
import br.gov.sp.fatec.pi.imobiliaria.repository.ImobiliariaRepository;
import br.gov.sp.fatec.pi.imobiliaria.repository.UsuarioRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.util.ObjectUtils.isEmpty;

@Service
public class UsuarioService {

  private final UsuarioRepository repository;
  private final ClienteRepository clienteRepository;
  private final ImobiliariaRepository imobiliariaRepository;
  private final ModelMappers mappers;


  public UsuarioService(final UsuarioRepository repository, final ClienteRepository clienteRepository, final ImobiliariaRepository imobiliariaRepository, final ModelMappers mappers) {
    this.repository = repository;
    this.clienteRepository = clienteRepository;
    this.imobiliariaRepository = imobiliariaRepository;
    this.mappers = mappers;
  }

  public Page<UsuarioResponse> listarClientes(final Pageable pageable) {
    final var usuarioPage = repository.findAll(pageable);
    return usuarioPage.map(mappers::map);
  }

  public Optional<UsuarioResponse> getUsuarioById(final Long id) {
    return mappers.mapOptionalUsuario( repository.findById(id));
  }

  public UsuarioResponse createUsuario(final UsuarioRequest usuarioRequest) {
    final var user = repository.findUsuarioByUsernameIgnoreCase(usuarioRequest.getUsername());
    if (user.isPresent()) {
      throw UsuarioException.usuarioExistente(usuarioRequest.getUsername());
    }
    final var usuario = Usuario.builder()
        .name(usuarioRequest.getName())
        .password(passwordEncoder().encode(usuarioRequest.getPassword()))
        .username(usuarioRequest.getUsername())
        .build();

    setEntities(usuarioRequest, usuario);

    final var result = repository
        .save(usuario);


    return mappers.map(result);
  }

  private void setEntities(final UsuarioRequest usuarioRequest, final Usuario usuario) {
    if(!isEmpty(usuarioRequest.getCliente())){
      usuario.setCliente(clienteRepository.save(mappers.map(usuarioRequest.getCliente())));
    }
    if(!isEmpty(usuarioRequest.getImobiliaria())){
      usuario.setImobiliaria(imobiliariaRepository.save(mappers.map(usuarioRequest.getImobiliaria())));
    }
  }

  public UsuarioResponse updateUsuario(final Long id, final UsuarioRequest usuarioRequest) {
    final var usuario = repository.findById(id).orElseThrow(() -> UsuarioException.usuarioNaoEncontrado(id));
    final var checkUsername = repository.findUsuarioByUsernameIgnoreCase(usuarioRequest.getUsername());
    if(checkUsername.isPresent()){
      throw UsuarioException.usuarioExistente(usuarioRequest.getUsername());
    }
    usuario.setName(usuarioRequest.getName());
    usuario.setUsername(usuarioRequest.getUsername());
    usuario.setPassword(passwordEncoder().encode(usuarioRequest.getPassword()));
    return mappers.map(repository.save(usuario));
  }

  public void deleteUsuarioComId(final Long id) {
     repository.deleteById(id);
  }

  private PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }
}
