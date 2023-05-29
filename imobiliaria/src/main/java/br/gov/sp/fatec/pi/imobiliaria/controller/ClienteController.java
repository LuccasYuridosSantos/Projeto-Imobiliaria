package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.Cliente;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.request.ClienteRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.response.ClienteResponse;
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

import jakarta.validation.Valid;

/**
 * A classe ClienteController representa o controlador REST para as operações
 * relacionadas à entidade Cliente.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	private final ClienteService clienteService;

	public ClienteController(final ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@PostMapping
	public ResponseEntity<ClienteResponse> criarCliente(@RequestBody @Valid final ClienteRequest clienteRequest) {
		final var novoCliente = clienteService.salvar(clienteRequest);
		return new ResponseEntity<>(novoCliente, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ClienteResponse> buscarClientePorId(@PathVariable final Long id) {
		final var response = clienteService.buscarPorId(id);
		return response.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@GetMapping
	public ResponseEntity<Page<Cliente>> listaClientes(final Pageable pageable) {
		return new ResponseEntity<>(clienteService.listarClientes(pageable), HttpStatus.OK);
	}


	@PutMapping("/{id}")
	public ResponseEntity<ClienteResponse> atualizarCliente(@PathVariable final Long id,
			@RequestBody final ClienteRequest clienteRequest) {
		final var clienteAtualizadoSalvo = clienteService.atualizar(id, clienteRequest);
		if (ObjectUtils.isEmpty(clienteAtualizadoSalvo)) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(clienteAtualizadoSalvo, HttpStatus.OK);
	}

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
