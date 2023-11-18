package tp.exception;

public class DatosObligatoriosAusentesException extends Exception {
	@Override
	public String getMessage() {
		String message = "Existen datos obligatorios que no fueron completados. Por favor, revise.";
		return message;
	}
}
