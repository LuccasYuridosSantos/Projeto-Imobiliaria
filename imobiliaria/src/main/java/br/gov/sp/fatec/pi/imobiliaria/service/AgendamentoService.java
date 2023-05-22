package br.gov.sp.fatec.pi.imobiliaria.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.gov.sp.fatec.pi.imobiliaria.exception.AgendamentoException;
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
	 * Buscar todos agendamentos existe no repositório a partir de uma determinada data
	 * e hora.
	 * 
	 * @param dataHora A data e hora do agendamentos a ser procurado.
	 * @return Lista de agendamentos para a data e hora informadas.
	 */
	public List<Agendamento> buscarAgendamentos(LocalDateTime dataHora) {
		List<Agendamento> agendamentos = agendamentoRepo.findAllFromDataHora(dataHora);
		return agendamentos;
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
	 * Buscar um agendamento no repositório.
	 * 
	 * @param id Do agendamento a ser procurado.
	 * @return O agendamento.
	 */
	public Optional<Agendamento> buscarAgendamentoPorId(final Long id) {		
		return agendamentoRepo.findById(id);
	}

	/**
	 * Salva um agendamento no repositório.
	 * 
	 * @param agendamento O agendamento a ser salvo.
	 * @return O agendamento salvo.
	 */
	@Transactional(rollbackFor = Exception.class)
	public Agendamento salvarAgendamento(Agendamento agendamento) {
		if(agendamento.getDataHora().isBefore(LocalDateTime.now())){
			throw AgendamentoException.periodoInvalido();
		}else if(!CollectionUtils.isEmpty(agendamentoRepo.findByDataHora(agendamento.getDataHora()))){
			throw AgendamentoException.agendamentoJaExistente();
		}
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
