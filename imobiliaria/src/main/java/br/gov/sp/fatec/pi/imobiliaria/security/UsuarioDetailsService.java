package br.gov.sp.fatec.pi.imobiliaria.security;

import br.gov.sp.fatec.pi.imobiliaria.model.Usuario;
import br.gov.sp.fatec.pi.imobiliaria.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * Classe de serviço para carregamento de detalhes do usuário.
 */
@Service
public class UsuarioDetailsService implements UserDetailsService {

  private UsuarioRepository usuarioRepository;

  /**
   * Construtor da classe UsuarioDetailsService.
   *
   * @param usuarioRepository  Repositório de usuários
   */
  public UsuarioDetailsService(final UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  /**
   * Carrega os detalhes do usuário com base no nome de usuário.
   *
   * @param username  Nome de usuário
   * @return          Detalhes do usuário (implementação UserDetails)
   * @throws UsernameNotFoundException  Exceção lançada se o usuário não for encontrado
   */
  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    var usuario = usuarioRepository.findByUsername(username);
    usuario.orElseThrow(() -> new UsernameNotFoundException(username + " não encontrado"));

    return usuario.map(Usuario::new).get();
  }

}
