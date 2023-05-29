package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.Imovel;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.request.ImovelRequest;
import br.gov.sp.fatec.pi.imobiliaria.model.vo.response.ImovelResponse;
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
 * 
 * @author [Nome]
 * @version [Versão]
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/imovel")
public class ImovelController {

    private final ImovelService service;

    public ImovelController(ImovelService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Imovel> buscarImovel(@PathVariable final Long id){
        final var imovel = service.buscarImovelPorId(id);
        if(imovel.isPresent()){
            return new ResponseEntity<>(imovel.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    public ResponseEntity<Page<Imovel>> listaImoveis(final Pageable pageable) {
        return new ResponseEntity<>(service.listarImoveis(pageable), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ImovelResponse> criarImovel(@RequestBody ImovelRequest request) {
        final var novoImovel = service.criarImovel(request);
        return new ResponseEntity<>(novoImovel, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imovel> atualizarImovel(@PathVariable Long id, @RequestBody Imovel imovel) {
        if (!service.buscarImovelPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        imovel.setId(id);
        Imovel imovelAtualizado = service.atualizarImovel(imovel);
        return new ResponseEntity<>(imovelAtualizado, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirImovel(@PathVariable Long id) {
        if (!service.buscarImovelPorId(id).isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        service.excluirImovel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



}
