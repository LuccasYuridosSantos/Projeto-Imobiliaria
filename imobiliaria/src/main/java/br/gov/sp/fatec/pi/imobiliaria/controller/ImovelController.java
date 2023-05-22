package br.gov.sp.fatec.pi.imobiliaria.controller;

import br.gov.sp.fatec.pi.imobiliaria.model.Imovel;
import br.gov.sp.fatec.pi.imobiliaria.repository.ImovelRepository;
import br.gov.sp.fatec.pi.imobiliaria.service.ImovelService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Classe que representa o controlador REST para o modelo Imovel.
 * 
 * @author [Nome]
 * @version [Versão]
 */
@RestController
@RequestMapping("/imovel")
public class ImovelController {

    private final ImovelService service;

    public ImovelController(ImovelService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Page<Imovel>> listaImoveis(final Pageable pageable) {
       return new ResponseEntity<>((service.listarImoveis(pageable)), HttpStatus.OK);
    }

}
