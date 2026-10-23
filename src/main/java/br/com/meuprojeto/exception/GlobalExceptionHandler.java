package br.com.meuprojeto.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(SoapTimeoutException.class)
    public ProblemDetail handleSoapTimeout(SoapTimeoutException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.GATEWAY_TIMEOUT, ex.getMessage());
        problem.setTitle("Gateway Timeout");
        problem.setType(URI.create("https://api.exemplo.com/errors/gateway-timeout"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(SoapIntegrationException.class)
    public ProblemDetail handleSoapIntegration(SoapIntegrationException ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_GATEWAY, ex.getMessage());
        problem.setTitle("Bad Gateway");
        problem.setType(URI.create("https://api.exemplo.com/errors/bad-gateway"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGeneralException(Exception ex) {
        ProblemDetail problem = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno não mapeado.");
        problem.setTitle("Internal Server Error");
        problem.setType(URI.create("https://api.exemplo.com/errors/internal-error"));
        problem.setProperty("timestamp", Instant.now());
        return problem;
    }

}
