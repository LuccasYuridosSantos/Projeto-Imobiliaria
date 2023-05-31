package br.gov.sp.fatec.pi.imobiliaria.service;

import br.gov.sp.fatec.pi.imobiliaria.exception.ImovelException;
import br.gov.sp.fatec.pi.imobiliaria.mappers.ModelMappers;
import br.gov.sp.fatec.pi.imobiliaria.model.Imovel;
import br.gov.sp.fatec.pi.imobiliaria.model.request.ImovelRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.response.ImovelResponse;
import br.gov.sp.fatec.pi.imobiliaria.repository.ClienteRepository;
import br.gov.sp.fatec.pi.imobiliaria.repository.ImobiliariaRepository;
import br.gov.sp.fatec.pi.imobiliaria.repository.ImovelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Classe de serviço para manipulação de imóveis.
 */
@Service
public class ImovelService {

    private final ImovelRepository repository;
    private final ClienteRepository clienteRepository;
    private final ImobiliariaRepository imobiliariaRepository;

    private final ModelMappers modelMappers;

    /**
     * Construtor da classe ImovelService.
     *
     * @param repository             Repositório de imóveis
     * @param clienteRepository      Repositório de clientes
     * @param imobiliariaRepository  Repositório de imobiliárias
     * @param modelMappers           Mappers de modelos
     */
    public ImovelService(final ImovelRepository repository, final ClienteRepository clienteRepository,
                         final ImobiliariaRepository imobiliariaRepository, final ModelMappers modelMappers) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.imobiliariaRepository = imobiliariaRepository;
        this.modelMappers = modelMappers;
    }

    /**
     * Retorna um imóvel com o ID especificado.
     *
     * @param id  ID do imóvel
     * @return    Imóvel encontrado (opcional)
     */
    public Optional<Imovel> buscarImovelPorId(final Long id) {
        return repository.findById(id);
    }

    /**
     * Retorna uma página de imóveis.
     *
     * @param pageable  Configurações da página
     * @return          Página de imóveis
     */
    public Page<Imovel> listarImoveis(final Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Cria um novo imóvel com base nos dados fornecidos.
     *
     * @param request  Dados do imóvel a ser criado
     * @return         Imóvel criado (no formato de resposta)
     * @throws ImovelException se ocorrer um erro durante a criação do imóvel
     */
    public ImovelResponse criarImovel(final ImovelRequest request) throws ImovelException {
        final var imovel = modelMappers.map(request);

        if (request.getCodProprietario() != null) {
            final var proprietario = clienteRepository.findById(request.getCodProprietario())
                    .orElseThrow(() -> ImovelException.proprietarioNaoEncontrada(request.getCodProprietario()));
            imovel.setProprietario(proprietario);
        }
        if (request.getCodLocatario() != null) {
            final var locatario = clienteRepository.findById(request.getCodLocatario())
                    .orElseThrow(() -> ImovelException.locatarioNaoEncontrada(request.getCodLocatario()));
            imovel.setLocatario(locatario);
        }
        if (request.getCodImobiliaria() != null) {
            final var imobiliariaAtualizada = imobiliariaRepository.findById(request.getCodImobiliaria())
                    .orElseThrow(() -> ImovelException.imobiliariaNaoEncontrada(request.getCodImobiliaria()));
            imovel.setImobiliaria(imobiliariaAtualizada);
        }

        return modelMappers.map(repository.save(imovel));
    }

    /**
     * Atualiza um imóvel existente com os dados fornecidos.
     *
     * @param imovel  Imóvel a ser atualizado
     * @return        Imóvel atualizado
     */
    public Imovel atualizarImovel(final Imovel imovel) {
        return repository.save(imovel);
    }

    /**
     * Exclui um imóvel com o ID especificado.
     *
     * @param id  ID do imóvel a ser excluído
     */
    public void excluirImovel(final Long id) {
        repository.deleteById(id);
    }
}
