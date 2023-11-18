package tp.exception;

public class ValoresParaVehiculoExistentesException extends Exception {
	@Override
	public String getMessage() {
		String message = "Los datos identificatorios del vehiculo (motor, chasis y patente) corresponden a un vehiculo ya asignado a una poliza vigente.";
		return message;
	}
}
