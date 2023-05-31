package br.gov.sp.fatec.pi.imobiliaria.mappers;

import br.gov.sp.fatec.pi.imobiliaria.model.Agendamento;
import br.gov.sp.fatec.pi.imobiliaria.model.Cliente;
import br.gov.sp.fatec.pi.imobiliaria.model.Corretor;
import br.gov.sp.fatec.pi.imobiliaria.model.Endereco;
import br.gov.sp.fatec.pi.imobiliaria.model.Imobiliaria;
import br.gov.sp.fatec.pi.imobiliaria.model.Imovel;
import br.gov.sp.fatec.pi.imobiliaria.model.Usuario;
import br.gov.sp.fatec.pi.imobiliaria.model.request.AgendamentoRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.request.ClienteRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.request.CorretorRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.request.EnderecoRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.request.ImobiliariaRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.request.ImovelRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.response.AgendamentoResponse;
import br.gov.sp.fatec.pi.imobiliaria.model.response.ClienteResponse;
import br.gov.sp.fatec.pi.imobiliaria.model.response.CorretorResponse;
import br.gov.sp.fatec.pi.imobiliaria.model.response.ImobiliariaResponse;
import br.gov.sp.fatec.pi.imobiliaria.model.response.ImovelResponse;
import br.gov.sp.fatec.pi.imobiliaria.model.response.UsuarioResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Mapper(componentModel = "spring",
      nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
      nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ModelMappers {

  Cliente map(ClienteRequest request);

  Imobiliaria map(ImobiliariaRequest request);

  Imovel map(ImovelRequest request);

  Agendamento map(AgendamentoRequest request);

  Endereco map(EnderecoRequest request);

  UsuarioResponse map(final Usuario usuario);

  CorretorResponse map(final Corretor corretor);

  default Optional<CorretorResponse> mapOptionalCorretor(Optional<Corretor> corretor){
    if(corretor.isPresent()){
      return Optional.of(map(corretor.get()));
    }
    return Optional.empty();
  }

  default Optional<UsuarioResponse> mapOptionalUsuario(Optional<Usuario> usuario){
    if(usuario.isPresent()){
      return Optional.of(map(usuario.get()));
    }
    return Optional.empty();
  }

  ClienteResponse map(final Cliente cliente);

  AgendamentoResponse map(final Agendamento agendamento);

  ImobiliariaResponse map(final Imobiliaria imobiliaria);

  Corretor map(final CorretorRequest corretorRequest);

  ImovelResponse map(final Imovel imovel);
}
