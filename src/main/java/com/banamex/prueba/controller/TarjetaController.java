package com.banamex.prueba.controller;

import com.banamex.prueba.dto.Card;
import com.banamex.prueba.dto.Id;
import com.banamex.prueba.enums.CodigoResponse;
import com.banamex.prueba.enums.CodigosRespuesta;
import com.banamex.prueba.enums.Folio;
import com.banamex.prueba.enums.ResponseService;
import com.banamex.prueba.log.EscribirLog;
import com.banamex.prueba.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Optional;

@RestController
public class TarjetaController {

    @Autowired
    private Folio folio;

    @Autowired
    private EscribirLog escribirLog;

    @Autowired
    CardRepository cardRepository;

    @PostMapping(path = "/test/savecard",
            consumes = "application/json",
            produces = "application/json")
    public Object saveCard(@RequestBody Card card) throws Exception{
        Card c = cardRepository.save(card);
        Id id = new Id();
        id.setId(c.getId());

        return new ResponseEntity(
                new ResponseService((CodigosRespuesta) CodigoResponse.CODIGO_200, folio.getFolio(), Arrays.asList(id)),CodigoResponse.CODIGO_200.getHttpStatus());
    }

    @PostMapping(path = "/id",
            consumes = "application/json",
            produces = "application/json")
    public Object searchCard(@RequestBody Id id) throws Exception{
        String searchId = id.getId();
        Optional<Card> c = cardRepository.findById(searchId);

        return new ResponseEntity(
                new ResponseService((CodigosRespuesta) CodigoResponse.CODIGO_200, folio.getFolio(), Arrays.asList(c)),CodigoResponse.CODIGO_200.getHttpStatus());
    }
}
