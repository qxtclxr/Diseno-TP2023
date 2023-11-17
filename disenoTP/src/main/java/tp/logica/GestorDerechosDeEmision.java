package tp.logica;

import tp.dao.DerechosDeEmisionDAO;
import tp.entidad.DerechosDeEmision;
import tp.entidad.ValorDerechosDeEmision;

public class GestorDerechosDeEmision {
	public static ValorDerechosDeEmision getDerechosDeEmisionActual() {
		DerechosDeEmisionDAO dao = new DerechosDeEmisionDAO();
		DerechosDeEmision derechos = dao.getAll().get(0);
		return derechos.getValorActualDerechosDeEmision();
	}
}
