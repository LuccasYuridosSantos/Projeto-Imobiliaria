package br.gov.sp.fatec.pi.imobiliaria.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import br.gov.sp.fatec.pi.imobiliaria.mappers.ModelMappers;
import br.gov.sp.fatec.pi.imobiliaria.model.Corretor;
import br.gov.sp.fatec.pi.imobiliaria.model.Imobiliaria;
import br.gov.sp.fatec.pi.imobiliaria.model.Imovel;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.request.AgendamentoRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.response.AgendamentoResponse;
import br.gov.sp.fatec.pi.imobiliaria.repository.ClienteRepository;
import br.gov.sp.fatec.pi.imobiliaria.repository.CorretorRepository;
import br.gov.sp.fatec.pi.imobiliaria.repository.ImobiliariaRepository;
import br.gov.sp.fatec.pi.imobiliaria.repository.ImovelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import br.gov.sp.fatec.pi.imobiliaria.exception.AgendamentoException;
import br.gov.sp.fatec.pi.imobiliaria.model.Agendamento;
import br.gov.sp.fatec.pi.imobiliaria.repository.AgendamentoRepository;

@Service
public class AgendamentoService {


	public static final String CLIENTE_NAO_ENCONTRADO = "Cliente não encontrado";
	public static final String CORRETOR_NAO_ENCONTRADO_PARA_O_AGENDAMENTO = "Corretor não encontrado para o agendamento";
	public static final String IMOVEL_NAO_ENCONTRADO_PARA_O_AGENDAMENTO = "Imovel não encontrado para o agendamento";
	public static final String IMOBILIARIA_NAO_ENCONTRADA_PARA_O_AGENDAMENTO = "Imobiliaria não encontrada para o agendamento";
	private final AgendamentoRepository agendamentoRepository;
	private final ClienteRepository clienteRepository;
	private final ImobiliariaRepository imobiliariaRepository;
	private final ImovelRepository imovelRepository;

	private final CorretorRepository corretorRepository;

	private final ModelMappers modelMappers;

	public AgendamentoService(final AgendamentoRepository agendamentoRepository, final ClienteRepository clienteRepository,
														final ImobiliariaRepository imobiliariaRepository, final ImovelRepository imovelRepository,
														final CorretorRepository corretorRepository, final ModelMappers modelMappers) {
		this.agendamentoRepository = agendamentoRepository;
		this.clienteRepository = clienteRepository;
		this.imobiliariaRepository = imobiliariaRepository;
		this.imovelRepository = imovelRepository;
		this.corretorRepository = corretorRepository;
		this.modelMappers = modelMappers;
	}

	public boolean agendamentoExiste(LocalDateTime dataHora) {
		List<Agendamento> agendamentos = agendamentoRepository.findByDataHora(dataHora);
		return !agendamentos.isEmpty();
	}

	public List<AgendamentoResponse> buscarAgendamentos(LocalDateTime dataHora) {
		List<Agendamento> agendamentos = agendamentoRepository.findAllFromDataHora(dataHora);
		return  agendamentos.stream().map(modelMappers::map).collect(Collectors.toList());
	}

	public boolean agendamentoExiste(Long clienteId, Long imovelId) {
		List<Agendamento> agendamentos = agendamentoRepository.findByClienteIdAndImovelId(clienteId, imovelId);
		return !agendamentos.isEmpty();
	}


	public Optional<AgendamentoResponse> buscarAgendamentoPorId(final Long id) {
		final var agendamento = agendamentoRepository.findById(id);
		if(agendamento.isPresent()){
			return Optional.of(modelMappers.map(agendamento.get()));
		}
		return Optional.empty();
	}


	public void excluirAgendamento(final Long agendamento) {
		agendamentoRepository.deleteById(agendamento);
	}

  public Page<AgendamentoResponse> listarAgendamentos(final Pageable pageable) {
		return agendamentoRepository.findAll(pageable).map(modelMappers::map);
  }

	@Transactional(rollbackFor = Exception.class)
	public AgendamentoResponse salvarAgendamento(final AgendamentoRequest request) {
		verificaDataHoraAgendamento(request.getDataHora());
		verificaAgendamentoExistente(request.getDataHora());

		final var cliente = buscaEntidadePorId(clienteRepository, request.getIdCliente(), CLIENTE_NAO_ENCONTRADO);
		final Corretor corretor = buscaEntidadePorId(corretorRepository, request.getIdCorretor(), CORRETOR_NAO_ENCONTRADO_PARA_O_AGENDAMENTO);
		final Imovel imovel = buscaEntidadePorId(imovelRepository, request.getIdImovel(), IMOVEL_NAO_ENCONTRADO_PARA_O_AGENDAMENTO);
		final Imobiliaria imobiliaria = buscaEntidadePorId(imobiliariaRepository, request.getIdImobiliaria(), IMOBILIARIA_NAO_ENCONTRADA_PARA_O_AGENDAMENTO);

		final var agendamento = Agendamento.builder()
				.dataHora(request.getDataHora())
				.descricao(request.getDescricao())
				.cliente(cliente)
				.corretor(corretor)
				.imovel(imovel)
				.imobiliaria(imobiliaria)
				.build();

		return modelMappers.map(agendamentoRepository.save(agendamento));
	}

	private void verificaDataHoraAgendamento(LocalDateTime dataHora) {
		if(dataHora.isBefore(LocalDateTime.now())) {
			throw AgendamentoException.periodoInvalido();
		}
	}

	private void verificaAgendamentoExistente(LocalDateTime dataHora) {
		if(!CollectionUtils.isEmpty(agendamentoRepository.findByDataHora(dataHora))) {
			throw AgendamentoException.agendamentoJaExistente();
		}
	}

	private <T> T buscaEntidadePorId(JpaRepository<T, Long> repository, Long id, String mensagemErro) {
		if(id != null) {
			return repository.findById(id).orElseThrow(() -> AgendamentoException.naoEncontradoParaAgendamento(mensagemErro));
		}
		return null;
	}
}
