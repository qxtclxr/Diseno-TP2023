package tp.exception;

public class AutoMuyViejoParaCoberturaElegidaException extends Exception {
	@Override
	public String getMessage() {
		String message = "El modelo de vehiculo elegido tiene mas de 10 años de antiguedad. Tales vehiculos solo puden acceder a al tipo de cobertura \"Responsabilidad Civil\"";
		return message;
	}
}
