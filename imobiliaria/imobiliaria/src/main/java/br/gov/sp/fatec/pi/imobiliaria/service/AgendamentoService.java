package br.gov.sp.fatec.pi.imobiliaria.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.gov.sp.fatec.pi.imobiliaria.model.Agendamento;
import br.gov.sp.fatec.pi.imobiliaria.repository.AgendamentoRepository;

/**
 * A classe AgendamentoService representa um serviço que é responsável por
 * realizar validações e manipulações relacionadas ao agendamento.
 */
@Service
public class AgendamentoService {

	@Autowired
	private AgendamentoRepository agendamentoRepo;

	/**
	 * Verifica se um agendamento já existe no repositório para uma determinada data
	 * e hora.
	 * 
	 * @param dataHora A data e hora do agendamento a ser verificado.
	 * @return true se um agendamento já existe para a data e hora informadas, false
	 *         caso contrário.
	 */
	public boolean agendamentoExiste(LocalDateTime dataHora) {
		List<Agendamento> agendamentos = agendamentoRepo.findByDataHora(dataHora);
		return !agendamentos.isEmpty();
	}

	/**
	 * Verifica se um agendamento já existe no repositório para um determinado
	 * cliente e imóvel.
	 * 
	 * @param clienteId O ID do cliente a ser verificado.
	 * @param imovelId  O ID do imóvel a ser verificado.
	 * @return true se um agendamento já existe para o cliente e imóvel informados,
	 *         false caso contrário.
	 */
	public boolean agendamentoExiste(Long clienteId, Long imovelId) {
		List<Agendamento> agendamentos = agendamentoRepo.findByClienteIdAndImovelId(clienteId, imovelId);
		return !agendamentos.isEmpty();
	}

	/**
	 * Salva um agendamento no repositório.
	 * 
	 * @param agendamento O agendamento a ser salvo.
	 * @return O agendamento salvo.
	 */
	public Agendamento salvarAgendamento(Agendamento agendamento) {
		return agendamentoRepo.save(agendamento);
	}

	/**
	 * Exclui um agendamento do repositório.
	 * 
	 * @param agendamento O agendamento a ser excluído.
	 */
	public void excluirAgendamento(Agendamento agendamento) {
		agendamentoRepo.delete(agendamento);
	}

}
