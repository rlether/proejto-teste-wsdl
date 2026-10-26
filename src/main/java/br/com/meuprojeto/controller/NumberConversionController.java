package br.com.meuprojeto.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.meuprojeto.service.NumberConversionService;

import java.math.BigInteger;
import java.util.Map;

@RestController
@RequestMapping
public class NumberConversionController {

    private final NumberConversionService numberConversionService;

    public NumberConversionController(NumberConversionService numberConversionService) {
        this.numberConversionService = numberConversionService;
    }

    @GetMapping("/ConverterNumeroPorExtenso/{numero}")
    public ResponseEntity<Map<String, Object>> converterNumeroPorExtenso(@PathVariable BigInteger numero) {
        String resultado = numberConversionService.converterNumeroParaPalavras(numero);
        return ResponseEntity.ok(Map.of(
                "numero", numero,
                "extenso", resultado
        ));
    }
}
