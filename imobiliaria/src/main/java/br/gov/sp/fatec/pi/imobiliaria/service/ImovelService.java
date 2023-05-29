package br.gov.sp.fatec.pi.imobiliaria.service;

import br.gov.sp.fatec.pi.imobiliaria.exception.ImovelException;
import br.gov.sp.fatec.pi.imobiliaria.mappers.ModelMappers;
import br.gov.sp.fatec.pi.imobiliaria.model.Imovel;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.request.ImovelRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.response.ImovelResponse;
import br.gov.sp.fatec.pi.imobiliaria.repository.ClienteRepository;
import br.gov.sp.fatec.pi.imobiliaria.repository.ImobiliariaRepository;
import br.gov.sp.fatec.pi.imobiliaria.repository.ImovelRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ImovelService {

    private final ImovelRepository repository;
    private final ClienteRepository clienteRepository;
    private final ImobiliariaRepository imobiliariaRepository;

    private final ModelMappers modelMappers;

    public ImovelService(final ImovelRepository repository, final ClienteRepository clienteRepository,
                         final ImobiliariaRepository imobiliariaRepository, final ModelMappers modelMappers) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.imobiliariaRepository = imobiliariaRepository;
        this.modelMappers = modelMappers;
    }

    public Optional<Imovel> buscarImovelPorId(final Long id) {
        return repository.findById(id);
    }

    public Page<Imovel> listarImoveis(final Pageable pageable) {
        return repository.findAll(pageable);
    }

    public ImovelResponse criarImovel(final ImovelRequest request) {

        final var imovel = modelMappers.map(request);

        if(request.getCodProprietario() != null) {
            final var proprietario = clienteRepository.findById(request.getCodProprietario())
                .orElseThrow(() -> ImovelException.proprietarioNaoEncontrada(request.getCodProprietario()));
            imovel.setProprietario(proprietario);
        }
        if(request.getCodLocatario() != null){
            final var locatorio = clienteRepository.findById(request.getCodLocatario())
                .orElseThrow(() -> ImovelException.locatarioNaoEncontrada(request.getCodLocatario()));
            imovel.setLocatario(locatorio);
        }
        if(request.getCodImobiliaria() != null){
            final var imobiliariaAtualizada = imobiliariaRepository.findById(request.getCodImobiliaria())
                .orElseThrow(() -> ImovelException.imobiliariaNaoEncontrada(request.getCodImobiliaria()));
            imovel.setImobiliaria(imobiliariaAtualizada);
        }

        return modelMappers.map(repository.save(imovel));
    }

    public Imovel atualizarImovel(final Imovel imovel) {
        return repository.save(imovel);
    }

    public void excluirImovel(final Long id) {
        repository.deleteById(id);
    }
}
