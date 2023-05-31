package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.Cliente;
import br.gov.sp.fatec.pi.imobiliaria.model.request.ClienteRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.response.ClienteResponse;
import br.gov.sp.fatec.pi.imobiliaria.service.ClienteService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * A classe ClienteController representa o controlador REST para as operações relacionadas à entidade Cliente.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	/**
	 * Construtor da classe ClienteController.
	 *
	 * @param clienteService O serviço responsável pela lógica de negócio dos clientes
	 */
	public ClienteController(final ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	/**
	 * Endpoint para criar um novo cliente.
	 *
	 * @param clienteRequest O objeto ClienteRequest contendo os dados do cliente a ser criado
	 * @return Uma ResponseEntity contendo o cliente criado e o status HTTP 201 Created
	 */
	@PostMapping
	public ResponseEntity<ClienteResponse> criarCliente(@RequestBody @Valid final ClienteRequest clienteRequest) {
		final var novoCliente = clienteService.salvar(clienteRequest);
		return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
	}

	/**
	 * Endpoint para buscar um cliente pelo seu ID.
	 *
	 * @param id O ID do cliente a ser buscado
	 * @return Uma ResponseEntity contendo o cliente encontrado e o status HTTP 200 OK,
	 *         ou o status HTTP 404 Not Found caso não seja encontrado um cliente com o ID fornecido
	 */
	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponse> buscarClientePorId(@PathVariable final Long id) {
		final var response = clienteService.buscarPorId(id);
		return response.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
				.orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	/**
	 * Endpoint para listar todos os clientes.
	 *
	 * @param pageable O objeto Pageable para paginação dos resultados
	 * @return Uma ResponseEntity contendo a lista de clientes e o status HTTP 200 OK
	 */
	@GetMapping
	public ResponseEntity<Page<Cliente>> listaClientes(final Pageable pageable) {
		return new ResponseEntity<>(clienteService.listarClientes(pageable), HttpStatus.OK);
	}

	/**
	 * Endpoint para atualizar os dados de um cliente.
	 *
	 * @param id             O ID do cliente a ser atualizado
	 * @param clienteRequest O objeto ClienteRequest contendo os novos dados do cliente
	 * @return Uma ResponseEntity contendo o cliente atualizado e o status HTTP 200 OK,
	 *         ou o status HTTP 404 Not Found caso não seja encontrado um cliente com o ID fornecido
	 */
	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable final Long id,
															@RequestBody final ClienteRequest clienteRequest) {
		final var clienteAtualizadoSalvo = clienteService.atualizar(id, clienteRequest);
		if (ObjectUtils.isEmpty(clienteAtualizadoSalvo)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clienteAtualizadoSalvo, HttpStatus.OK);
	}

	/**
	 * Endpoint para remover um cliente pelo seu ID.
	 *
	 * @param id O ID do cliente a ser removido
	 * @return Uma ResponseEntity vazia com o status HTTP 204 No Content caso o cliente seja removido com sucesso,
	 *         ou o status HTTP 404 Not Found caso não seja encontrado um cliente com o ID fornecido
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerCliente(@PathVariable final Long id) {
		final var clienteExistente = clienteService.buscarPorId(id);
		if (clienteExistente.isPresent()) {
			clienteService.remover(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
