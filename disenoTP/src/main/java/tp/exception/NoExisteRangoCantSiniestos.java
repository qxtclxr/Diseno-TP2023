package tp.exception;

public class NoExisteRangoCantSiniestos extends Exception {

	@Override
	public String getMessage() {
		String message = "No existe un rango de cantidad de siniestros para esa cantidad minima.";
		return message;
	}
	
}
