package br.com.meuprojeto.config;

import com.exemplo.continentes.soap.client.CountryInfoService;
import com.exemplo.continentes.soap.client.CountryInfoServiceSoapType;
import com.exemplo.continentes.soap.numberconversion.NumberConversion;
import com.exemplo.continentes.soap.numberconversion.NumberConversionSoapType;
import jakarta.xml.ws.BindingProvider;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.net.URL;

@Configuration
public class SoapClientConfig {

	@Value("${soap.country-info.endpoint-url}")
    private String countryEndpointUrl;

    @Value("${soap.country-info.connect-timeout-ms:5000}")
    private int countryConnectTimeoutMs;

    @Value("${soap.country-info.read-timeout-ms:10000}")
    private int countryReadTimeoutMs;

    @Value("${soap.number-conversion.endpoint-url}")
    private String numberEndpointUrl;

    @Value("${soap.number-conversion.connect-timeout-ms:5000}")
    private int numberConnectTimeoutMs;

    @Value("${soap.number-conversion.read-timeout-ms:10000}")
    private int numberReadTimeoutMs;

    @Bean
    public CountryInfoServiceSoapType countryInfoServiceSoapType() {
        try {
            URL wsdlUrl = new ClassPathResource("wsdl/CountryInfoService.wsdl").getURL();
            CountryInfoService service = new CountryInfoService(wsdlUrl);
            CountryInfoServiceSoapType port = service.getCountryInfoServiceSoap();
            configureBinding((BindingProvider) port, countryEndpointUrl, countryConnectTimeoutMs, countryReadTimeoutMs);
            return port;
        } catch (Exception ex) {
            throw new IllegalStateException("Falha ao inicializar o cliente CountryInfoService", ex);
        }
    }
    
    @Bean
    public NumberConversionSoapType numberConversionSoapType() {
        try {
            
        	URL wsdlUrl = new ClassPathResource("wsdl/NumberConversion.wsdl").getURL();
            NumberConversion service = new NumberConversion(wsdlUrl);
            NumberConversionSoapType port = service.getNumberConversionSoap();
            
            configureBinding((BindingProvider) port, numberEndpointUrl, numberConnectTimeoutMs, numberReadTimeoutMs);
            
            return port;
            
        } catch (Exception ex) {
            throw new IllegalStateException("Falha ao inicializar o cliente NumberConversion", ex);
        }
    }

    private void configureBinding(BindingProvider provider, String endpointUrl, int connectTimeout, int readTimeout) {
        var context = provider.getRequestContext();
        context.put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, endpointUrl);
        context.put("jakarta.xml.ws.client.connectionTimeout", connectTimeout);
        context.put("jakarta.xml.ws.client.receiveTimeout", readTimeout);
        context.put("com.sun.xml.ws.connect.timeout", connectTimeout);
        context.put("com.sun.xml.ws.request.timeout", readTimeout);
    }
}
