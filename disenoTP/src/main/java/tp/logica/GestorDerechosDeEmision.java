package tp.logica;

import tp.dao.DerechosDeEmisionDAO;
import tp.entidad.DerechosDeEmision;

public class GestorDerechosDeEmision {
	public ValorDerechosDeEmision getDerechosDeEmisionActual() {
		DerechosDeEmisionDAO dao = new DerechosDeEmisionDAO();
		DerechosDeEmision derechos = dao.getAll().get(0);
		return derechos.getValorActualDerechosDeEmision();
	}
}
