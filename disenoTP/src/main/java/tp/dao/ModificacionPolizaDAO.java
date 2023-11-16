package tp.dao;

import tp.entidad.ModificacionPoliza;

/*
 * Este dao es tambien un quilombo. Lo dejo implementado con la version generica, pero despues lo cambio.
 */
public class ModificacionPolizaDAO extends AbstractDAO<ModificacionPoliza> {
	public ModificacionPolizaDAO() {
		this.setClase(ModificacionPoliza.class);
	}
}
