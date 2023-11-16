package tp.dao;

import tp.entidad.Pago;
/*
 * Este DAO hay que retocarlo mucho. Dejo una version provisional
 */

public class PagoDAO extends AbstractDAO<Pago> {
	public PagoDAO() {
		this.setClase(Pago.class);
	}
}
