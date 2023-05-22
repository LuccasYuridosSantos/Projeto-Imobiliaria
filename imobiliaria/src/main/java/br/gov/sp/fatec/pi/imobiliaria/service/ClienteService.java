package br.gov.sp.fatec.pi.imobiliaria.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.sp.fatec.pi.imobiliaria.model.Cliente;
import br.gov.sp.fatec.pi.imobiliaria.repository.ClienteRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * A classe ClienteService representa um serviço que oferece operações
 * relacionadas ao cliente.
 * 
 * @author [Nome]
 * @version 1.0
 */
@Service
public class ClienteService {

	private final ClienteRepository repository;

	/**
	 * Construtor da classe ClienteService.
	 * 
	 * @param repository Repositório utilizado para acessar os dados de clientes no
	 *                   banco de dados.
	 */
	public ClienteService(ClienteRepository repository) {
		super();
		this.repository = repository;
	}

	/**
	 * Busca um cliente por id.
	 * 
	 * @param id Id do cliente a ser buscado.
	 * @return Optional que pode conter o cliente buscado ou vazio, caso não seja
	 *         encontrado.
	 */
	public Optional<Cliente> buscarPorId(final Long id) {
		return repository.findById(id);
	}

	/**
	 * Salva um cliente.
	 * 
	 * @param cliente Cliente a ser salvo.
	 * @return Cliente salvo.
	 */
	@Transactional(rollbackFor = Exception.class)
	public Cliente salvar(Cliente cliente) {
		return repository.save(cliente);
	}

	/**
	 * Atualiza um cliente existente.
	 * 
	 * @param clienteAtualizado Cliente com as informações atualizadas.
	 * @return Cliente atualizado.
	 */
	public Cliente atualizar(Cliente clienteAtualizado) {
		Cliente clienteExistente = buscarPorId(clienteAtualizado.getId())
				.orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

		clienteExistente.setNome(clienteAtualizado.getNome());
		clienteExistente.setEmail(clienteAtualizado.getEmail());
		clienteExistente.setEndereco(clienteAtualizado.getEndereco());
		clienteExistente.setTelefone(clienteAtualizado.getTelefone());
		clienteExistente.setTipoCliente(clienteAtualizado.getTipoCliente());

		return salvar(clienteExistente);
	}

	/**
	 * Busca todos os clientes cadastrados.
	 * 
	 * @return Lista contendo todos os clientes cadastrados.
	 */
	public List<Cliente> buscarTodos() {
		return (List<Cliente>) repository.findAll();
	}

	/**
	 * Remove um cliente existente.
	 * 
	 * @param clienteExistente Cliente a ser removido.
	 */
	public void remover(Optional<Cliente> clienteExistente) {
		clienteExistente.ifPresent(repository::delete);
	}

}
