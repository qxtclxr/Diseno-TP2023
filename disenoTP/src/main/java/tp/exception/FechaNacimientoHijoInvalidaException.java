package tp.exception;

public class FechaNacimientoHijoInvalidaException extends Exception {
	@Override
	public String getMessage() {
		String message = "La fecha de nacimiento ingresada no corresponde a una persona cuya edad se encuentra entre los 18 y los 30 años.";
		return message;
	}
}
