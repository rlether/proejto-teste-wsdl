package br.com.meuprojeto.service;

import jakarta.xml.ws.WebServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.exemplo.continentes.soap.client.ArrayOftContinent;
import com.exemplo.continentes.soap.client.CountryInfoServiceSoapType;
import com.exemplo.continentes.soap.client.TContinent;

import br.com.meuprojeto.exception.SoapIntegrationException;
import br.com.meuprojeto.exception.SoapTimeoutException;

import java.net.SocketTimeoutException;
import java.util.List;
import java.util.Objects;

@Service
public class ContinentService {

    private static final Logger log = LoggerFactory.getLogger(ContinentService.class);
    private final CountryInfoServiceSoapType soapClient;

    public ContinentService(CountryInfoServiceSoapType soapClient) {
        this.soapClient = soapClient;
    }

    public List<String> obterNomesContinentes() {
        try {
            log.info("Chamando operação ListOfContinentsByName no serviço SOAP...");
            ArrayOftContinent response = soapClient.listOfContinentsByName();

            if (response == null || response.getTContinent() == null) {
                return List.of();
            }

            return response.getTContinent().stream()
                    .map(TContinent::getSName)
                    .filter(Objects::nonNull)
                    .map(String::trim)
                    .filter(nome -> !nome.isBlank())
                    .toList();

        } catch (WebServiceException ex) {
            if (ex.getCause() instanceof SocketTimeoutException) {
                log.error("Timeout na comunicação com o serviço SOAP: {}", ex.getMessage());
                throw new SoapTimeoutException("Tempo limite excedido ao se comunicar com o serviço SOAP", ex);
            }
            log.error("Falha ao consumir o serviço SOAP: {}", ex.getMessage(), ex);
            throw new SoapIntegrationException("Erro retornado pelo serviço SOAP CountryInfoService", ex);
        } catch (Exception ex) {
            log.error("Erro inesperado durante a integração SOAP: {}", ex.getMessage(), ex);
            throw new SoapIntegrationException("Erro inesperado ao consultar a lista de continentes", ex);
        }
    }

}
