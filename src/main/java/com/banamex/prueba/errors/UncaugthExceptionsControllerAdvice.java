package com.banamex.prueba.errors;

import com.banamex.prueba.enums.CodigoResponse;
import com.banamex.prueba.enums.Folio;
import com.banamex.prueba.log.EscribirLog;
import com.banamex.prueba.log.Nivel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@ControllerAdvice(annotations = {RestController.class})
public class UncaugthExceptionsControllerAdvice {

    @Autowired
    private Folio folio;

    @Autowired
    private EscribirLog escribirLog;


    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity handleHttpMessageNotReadableException(Exception e){
        ErrorDescription[] errorres = { new ErrorDescription("Problemas en el microservicio",
                Arrays.asList(new String[] {"La peticion no es correcta"}))};
        escribirLog.escribir(getClass(), Nivel.ERROR, "Exception: " + e);
        ResponseError response = new ResponseError(CodigoResponse.CODIGO_400.getDescripcion(),
                CodigoResponse.CODIGO_400.getDescripcion(), folio.getFolio(),
                "https://banamex.com/errores#" + CodigoResponse.CODIGO_400.getCodigo(),
                Arrays.asList(errorres));
        return new ResponseEntity(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity handleException(Exception e){
        ErrorDescription[] errorres = { new ErrorDescription("Problemas en el microservicio",
                Arrays.asList(new String[] {"Fallo al escribir peticion al archivo"}))};
        escribirLog.escribir(getClass(), Nivel.ERROR, "Exception: " + e);
        ResponseError response = new ResponseError(CodigoResponse.CODIGO_500.getDescripcion(),
                CodigoResponse.CODIGO_500.getDescripcion(), folio.getFolio(),
                "https://banamex.com/errores#" + CodigoResponse.CODIGO_500.getCodigo(),
                Arrays.asList(errorres));
        return new ResponseEntity(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}