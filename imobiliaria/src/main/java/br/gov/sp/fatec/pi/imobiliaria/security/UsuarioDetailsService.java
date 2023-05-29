package br.gov.sp.fatec.pi.imobiliaria.security;

import br.gov.sp.fatec.pi.imobiliaria.exception.UsuarioException;
import br.gov.sp.fatec.pi.imobiliaria.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioDetailsService implements UserDetailsService {

  private UsuarioRepository usuarioRepository;

  public UsuarioDetailsService(final UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  @Override
  public UserDetails loadUserByUsername(String username) {
    return usuarioRepository.findUsuarioByUsernameIgnoreCase(username)
        .orElseThrow(() -> UsuarioException.usuarioNaoEncontado(username));
  }

}
