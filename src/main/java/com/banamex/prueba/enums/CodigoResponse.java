package com.banamex.prueba.enums;

import org.springframework.http.HttpStatus;

public enum CodigoResponse implements CodigosRespuesta{
    CODIGO_200("200.Banamex.001200", "Proceso terminado correctamente.", HttpStatus.OK),
    CODIGO_400("400.Banamex.000400", "Datos de entrada incorrectos, favor de validar.", HttpStatus.BAD_REQUEST),
    CODIGO_404("404.Banamex.000404", "Recurso no encontrado.", HttpStatus.NOT_FOUND),
    CODIGO_500("500.Banamex.000500", "Fallo en el servidor al procesar la peticion", HttpStatus.INTERNAL_SERVER_ERROR);

    private String codigo;

    private String descripcion;

    private HttpStatus httpStatus;

    CodigoResponse(String codigo, String descripcion, HttpStatus httpStatus) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.httpStatus = httpStatus;
    }

    public String getCodigo() { return codigo; }
    public String getDescripcion() { return descripcion; }
    public HttpStatus getHttpStatus() { return httpStatus; }
}

