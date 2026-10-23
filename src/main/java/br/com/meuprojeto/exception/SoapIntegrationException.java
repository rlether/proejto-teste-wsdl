package br.com.meuprojeto.exception;

public class SoapIntegrationException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public SoapIntegrationException(String message, Throwable cause) {
        super(message, cause);
    }
}
