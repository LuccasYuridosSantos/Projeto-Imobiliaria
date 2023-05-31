package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.Imovel;
import br.gov.sp.fatec.pi.imobiliaria.model.request.ImovelRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.response.ImovelResponse;
import br.gov.sp.fatec.pi.imobiliaria.service.ImovelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe que representa o controlador REST para o modelo Imovel.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/imovel")
public class ImovelController {

    private final ImovelService service;

    /**
     * Construtor da classe ImovelController.
     *
     * @param service O serviço responsável pela lógica de negócio dos imóveis
     */
    public ImovelController(ImovelService service) {
        this.service = service;
    }

    /**
     * Endpoint para buscar um imóvel pelo seu ID.
     *
     * @param id O ID do imóvel a ser buscado
     * @return Uma ResponseEntity contendo o imóvel encontrado e o status HTTP 200 OK,
     *         ou o status HTTP 404 Not Found caso não seja encontrado um imóvel com o ID fornecido
     */
    @GetMapping("/{id}")
    public ResponseEntity<Imovel> buscarImovel(@PathVariable final Long id){
        final var imovel = service.buscarImovelPorId(id);
        if(imovel.isPresent()){
            return new ResponseEntity<>(imovel.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Endpoint para listar todos os imóveis.
     *
     * @param pageable O objeto Pageable para paginação dos resultados
     * @return Uma ResponseEntity contendo a lista de imóveis e o status HTTP 200 OK
     */
    @GetMapping
    public ResponseEntity<Page<Imovel>> listaImoveis(final Pageable pageable) {
        return new ResponseEntity<>(service.listarImoveis(pageable), HttpStatus.OK);
    }

    /**
     * Endpoint para criar um novo imóvel.
     *
     * @param request O objeto ImovelRequest contendo os dados do imóvel a ser criado
     * @return Uma ResponseEntity contendo o imóvel criado e o status HTTP 201 Created
     */
    @PostMapping
    public ResponseEntity<ImovelResponse> criarImovel(@RequestBody ImovelRequest request) {
        final var novoImovel = service.criarImovel(request);
        return new ResponseEntity<>(novoImovel, HttpStatus.CREATED);
    }

    /**
     * Endpoint para atualizar um imóvel.
     *
     * @param id     O ID do imóvel a ser atualizado
     * @param imovel O objeto Imovel contendo os novos dados do imóvel
     * @return Uma ResponseEntity contendo o imóvel atualizado e o status HTTP 200 OK,
     *         ou o status HTTP 404 Not Found caso não seja possível atualizar o imóvel
     */
    @PutMapping("/{id}")
    public ResponseEntity<Imovel> atualizarImovel(@PathVariable Long id, @RequestBody Imovel imovel) {
        if (!service.buscarImovelPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        imovel.setId(id);
        Imovel imovelAtualizado = service.atualizarImovel(imovel);
        return new ResponseEntity<>(imovelAtualizado, HttpStatus.OK);
    }

    /**
     * Endpoint para excluir um imóvel.
     *
     * @param id O ID do imóvel a ser excluído
     * @return Uma ResponseEntity vazia com o status HTTP 204 No Content,
     *         ou o status HTTP 404 Not Found caso não seja possível encontrar o imóvel
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirImovel(@PathVariable Long id) {
        if (!service.buscarImovelPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.excluirImovel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
