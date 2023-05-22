package br.gov.sp.fatec.pi.imobiliaria.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.gov.sp.fatec.pi.imobiliaria.model.Cliente;
import br.gov.sp.fatec.pi.imobiliaria.service.ClienteService;

/**
 * A classe ClienteController representa o controlador REST para as operações
 * relacionadas à entidade Cliente.
 */
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	/**
	 * Cria um novo cliente.
	 * 
	 * @param cliente O cliente a ser criado.
	 * @return O objeto ResponseEntity com o novo cliente criado e o status HTTP
	 *         CREATED.
	 */
	@PostMapping
	public ResponseEntity<Cliente> criarCliente(@RequestBody final Cliente cliente) {
		Cliente novoCliente = clienteService.salvar(cliente);
		return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
	}

	/**
	 * Busca um cliente pelo seu ID.
	 * 
	 * @param id O ID do cliente a ser buscado.
	 * @return O objeto ResponseEntity com o cliente encontrado e o status HTTP OK,
	 *         ou o status HTTP NOT_FOUND caso o cliente não seja encontrado.
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> buscarClientePorId(@PathVariable final Long id) {
		final var cliente = clienteService.buscarPorId(id);
		if (cliente.isPresent()) {
			return new ResponseEntity<>(cliente.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Busca todos os clientes.
	 * 
	 * @return O objeto ResponseEntity com a lista de clientes encontrados e o
	 *         status HTTP OK.
	 */
	@GetMapping
	public ResponseEntity<List<Cliente>> buscarTodosClientes() {
		List<Cliente> clientes = clienteService.buscarTodos();
		return new ResponseEntity<>(clientes, HttpStatus.OK);
	}

	/**
	 * Atualiza um cliente existente.
	 * 
	 * @param id                O ID do cliente a ser atualizado.
	 * @param clienteAtualizado O objeto Cliente atualizado.
	 * @return O objeto ResponseEntity com o cliente atualizado e o status HTTP OK,
	 *         ou o status HTTP NOT_FOUND caso o cliente não seja encontrado.
	 */
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> atualizarCliente(@PathVariable final Long id,
			@RequestBody final Cliente clienteAtualizado) {
		final var clienteExistente = clienteService.buscarPorId(id);
		if (clienteExistente.isPresent()) {
			clienteAtualizado.setId(id);
			Cliente clienteAtualizadoSalvo = clienteService.atualizar(clienteAtualizado);
			return new ResponseEntity<>(clienteAtualizadoSalvo, HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	/**
	 * Remove um cliente existente.
	 * 
	 * @param id O ID do cliente a ser removido.
	 * @return O objeto ResponseEntity com o status HTTP NO_CONTENT, ou o status
	 *         HTTP NOT_FOUND caso o cliente não seja encontrado.
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> removerCliente(@PathVariable final Long id) {
		final var clienteExistente = clienteService.buscarPorId(id);
		if (clienteExistente.isPresent()) {
			clienteService.remover(clienteExistente);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
