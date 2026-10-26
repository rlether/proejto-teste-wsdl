package br.com.meuprojeto.service;

import java.math.BigInteger;
import java.net.SocketTimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.exemplo.continentes.soap.numberconversion.NumberConversionSoapType;

import br.com.meuprojeto.exception.SoapIntegrationException;
import br.com.meuprojeto.exception.SoapTimeoutException;
import jakarta.xml.ws.WebServiceException;

@Service
public class NumberConversionService {

    private static final Logger log = LoggerFactory.getLogger(NumberConversionService.class);
    private final NumberConversionSoapType soapClient;

    public NumberConversionService(NumberConversionSoapType soapClient) {
        this.soapClient = soapClient;
    }

    public String converterNumeroParaPalavras(BigInteger numero) {
        if (numero == null || numero.compareTo(BigInteger.ZERO) < 0) {
            throw new IllegalArgumentException("O número deve ser um inteiro positivo ou zero.");
        }

        try {
            log.info("Chamando operação NumberToWords para o número: {}", numero);
            String resultado = soapClient.numberToWords(numero);

            return (resultado != null) ? resultado.trim() : "";
        } catch (WebServiceException ex) {
            if (ex.getCause() instanceof SocketTimeoutException) {
                log.error("Timeout na comunicação com NumberConversion: {}", ex.getMessage());
                throw new SoapTimeoutException("Tempo limite excedido no serviço NumberConversion", ex);
            }
            log.error("Falha ao chamar NumberConversion: {}", ex.getMessage(), ex);
            throw new SoapIntegrationException("Erro retornado pelo serviço SOAP NumberConversion", ex);
        } catch (Exception ex) {
            log.error("Erro inesperado durante a conversão do número: {}", ex.getMessage(), ex);
            throw new SoapIntegrationException("Erro inesperado ao converter número para extenso", ex);
        }
    }
}