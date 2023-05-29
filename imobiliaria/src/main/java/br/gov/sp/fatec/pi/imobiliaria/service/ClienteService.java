package br.gov.sp.fatec.pi.imobiliaria.service;

import br.gov.sp.fatec.pi.imobiliaria.mappers.ModelMappers;
import br.gov.sp.fatec.pi.imobiliaria.model.Cliente;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.request.ClienteRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.response.ClienteResponse;
import br.gov.sp.fatec.pi.imobiliaria.repository.ClienteRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Optional;


@Service
public class ClienteService {

	private final ClienteRepository repository;
	private final ModelMappers modelMappers;
	private final ObjectMapper objectMapper;

	public ClienteService(final ClienteRepository repository, final ModelMappers modelMappers, final ObjectMapper objectMapper) {
		this.repository = repository;
		this.modelMappers = modelMappers;
		this.objectMapper = objectMapper;
	}


	public Optional<ClienteResponse> buscarPorId(final Long id) {
		final var cliente = repository.findById(id);
		if(cliente.isPresent()){
			return Optional.of(modelMappers.map(cliente.get()));
		}
		return Optional.empty();
	}


	@Transactional(rollbackFor = Exception.class)
	public ClienteResponse salvar(final ClienteRequest clienteRequest) {
		final var cliente = modelMappers.map(clienteRequest);
		return modelMappers.map(repository.save(cliente));
	}


	public ClienteResponse atualizar(final Long id, final ClienteRequest clienteRequest) {
		final var cliente = repository.findById(id);
		if(cliente.isPresent()){
			final var clienteUpdate = cliente.get();
			replaceFields(clienteRequest, clienteUpdate);
			return  modelMappers.map(repository.save(cliente.get()));
		}
		return null;
	}


	public Page<ClienteResponse> buscarTodos(final Pageable pageable) {
		return repository.findAll(pageable).map(modelMappers::map);
	}


	public void remover(final Long id) {
		repository.deleteById(id);
	}

  public Page<Cliente> listarClientes(final Pageable pageable) {
		return repository.findAll(pageable);
  }

	private void replaceFields(final ClienteRequest clienteRequest, final Cliente clienteAtualizado) {
		if(!StringUtils.isEmpty(clienteRequest.getNome())){
			clienteAtualizado.setNome(clienteRequest.getNome());
		}
		if(!StringUtils.isEmpty(clienteRequest.getTelefone())){
			clienteAtualizado.setTelefone(clienteRequest.getTelefone());
		}
		if(!StringUtils.isEmpty(clienteRequest.getEmail())){
			clienteAtualizado.setEmail(clienteRequest.getEmail());
		}
		if(!ObjectUtils.isEmpty(clienteRequest.getTipoCliente())){
			clienteAtualizado.setTipoCliente(clienteRequest.getTipoCliente());
		}
		if(!StringUtils.isEmpty(clienteRequest.getCpf())){
			clienteAtualizado.setCpf(clienteRequest.getCpf());
		}
		if(!StringUtils.isEmpty(clienteRequest.getCnpj())){
			clienteAtualizado.setCnpj(clienteRequest.getCnpj());
		}
		if(!ObjectUtils.isEmpty(clienteRequest.getEndereco())){
			final var endeco = clienteAtualizado.getEndereco();
			final var enderecoRequest = clienteRequest.getEndereco();
			if(ObjectUtils.isEmpty(endeco.getRua())){
				endeco.setRua(enderecoRequest.getRua());
			}
			if(!ObjectUtils.isEmpty(endeco.getNumero())){
				endeco.setNumero(enderecoRequest.getNumero());
			}
			if(!StringUtils.isEmpty(endeco.getComplemento())){
				endeco.setComplemento(enderecoRequest.getComplemento());
			}
			if(!StringUtils.isEmpty(endeco.getBairro())){
				endeco.setBairro(enderecoRequest.getBairro());

			}
			if(!StringUtils.isEmpty(endeco.getCidade())){
				endeco.setCidade(enderecoRequest.getCidade());
			}
			if(!StringUtils.isEmpty(endeco.getEstado())){
				endeco.setEstado(enderecoRequest.getEstado());
			}
			if(!StringUtils.isEmpty(endeco.getCep())){
				endeco.setCep(enderecoRequest.getCep());
			}
			clienteAtualizado.setEndereco(endeco);
		}
	}
}
