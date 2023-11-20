package tp.logica;

import tp.entidad.TipoPoliza;
//comm
public class FacadeSistemaFinanciero {
	
	private static float BONIFICACION_POR_PAGO_SEMESTRAL = 5F;
	
	public static float getBonificacionPorTipoPoliza(TipoPoliza tipoPoliza) {
		switch(tipoPoliza) {
			case SEMESTRAL: return BONIFICACION_POR_PAGO_SEMESTRAL;
			default: return 0;
		}
	}
	
}
