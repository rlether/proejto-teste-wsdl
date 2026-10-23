package br.com.meuprojeto.exception;

public class SoapTimeoutException extends RuntimeException {
	
    private static final long serialVersionUID = 1L;

	public SoapTimeoutException(String message, Throwable cause) {
        super(message, cause);
    }

}
