package br.gov.sp.fatec.pi.imobiliaria.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gov.sp.fatec.pi.imobiliaria.service.AgendamentoService;
import br.gov.sp.fatec.pi.imobiliaria.model.Agendamento;

@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoService service;

    public AgendamentoController(final AgendamentoService service) {
        this.service = service;
    }


    @GetMapping("/agendamento/{dataHora}")
    public ResponseEntity<List<Agendamento>> buscarAgendamentos(@PathVariable final LocalDateTime dataHora) {
        return new ResponseEntity<>(service.buscarAgendamentos(dataHora), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<Agendamento> criarAgendamento(@RequestBody final Agendamento agendamento) {
        return new ResponseEntity<>(service.salvarAgendamento(agendamento), HttpStatus.CREATED);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Agendamento> criarAgendamento(@PathVariable final Long id) {
        final var clienteExistente = service.buscarAgendamentoPorId(id);
		if (clienteExistente.isPresent()) {
			service.excluirAgendamento(clienteExistente.get());
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    
}
