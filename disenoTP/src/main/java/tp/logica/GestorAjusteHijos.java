package tp.logica;

import tp.dao.AjusteHijosDAO;
import tp.entidad.AjusteHijos;
import tp.entidad.PorcentajeAjusteHijos;

public class GestorAjusteHijos {

	public static PorcentajeAjusteHijos getPorcentajeAjusteHijosActual() {
		AjusteHijosDAO dao = new AjusteHijosDAO();
		//AjusteHijos es singleton, entonces simplemente se busca el unico elemento que es el primero
		AjusteHijos ajusteHijos = dao.getAll().get(0);
		return ajusteHijos.getValorActualPorcentajeCantHijos();
	}

}
