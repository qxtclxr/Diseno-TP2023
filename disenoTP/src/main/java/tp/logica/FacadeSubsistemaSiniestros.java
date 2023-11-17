package tp.logica;

import java.util.Random;

import tp.entidad.Cliente;

//Esta clase simula respuestas de un subsistema de Siniestros
public class FacadeSubsistemaSiniestros {
	
	private static int MAX_SINIESTROS;
	
	public static int getSiniestrosByCliente(Cliente cliente) {
		Random rand = new Random();
		return rand.nextInt(MAX_SINIESTROS+1);
	}
	
	public static boolean tieneSiniestrosUltimoAnio(Cliente cliente) {
		char primerCaracterApellido = cliente.getApellido().charAt(0);
		return ((int) primerCaracterApellido) % 2 == 0;
	}
}
