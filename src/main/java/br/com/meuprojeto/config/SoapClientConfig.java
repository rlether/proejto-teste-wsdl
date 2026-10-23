package br.com.meuprojeto.config;

import com.exemplo.continentes.soap.client.CountryInfoService;
import com.exemplo.continentes.soap.client.CountryInfoServiceSoapType;
import jakarta.xml.ws.BindingProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.net.URL;

@Configuration
public class SoapClientConfig {

    @Value("${soap.country-info.endpoint-url}")
    private String endpointUrl;

    @Value("${soap.country-info.connect-timeout-ms:5000}")
    private int connectTimeoutMs;

    @Value("${soap.country-info.read-timeout-ms:10000}")
    private int readTimeoutMs;

    @Bean
    public CountryInfoServiceSoapType countryInfoServiceSoapType() {
        try {
            // Carrega o WSDL dos recursos internos (classpath)
            URL wsdlUrl = new ClassPathResource("wsdl/CountryInfoService.wsdl").getURL();
            CountryInfoService service = new CountryInfoService(wsdlUrl);
            CountryInfoServiceSoapType port = service.getCountryInfoServiceSoap();

            BindingProvider bindingProvider = (BindingProvider) port;
            var requestContext = bindingProvider.getRequestContext();

            // O endpoint da requisição continua apontando para o servidor HTTP real
            requestContext.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
            requestContext.put("jakarta.xml.ws.client.connectionTimeout", connectTimeoutMs);
            requestContext.put("jakarta.xml.ws.client.receiveTimeout", readTimeoutMs);
            requestContext.put("com.sun.xml.ws.connect.timeout", connectTimeoutMs);
            requestContext.put("com.sun.xml.ws.request.timeout", readTimeoutMs);

            return port;
        } catch (Exception ex) {
            throw new IllegalStateException("Falha ao inicializar o cliente SOAP a partir do WSDL local", ex);
        }
    }

}
