package tp.logica;

import tp.entidad.*;
import tp.dto.*;
import java.util.List;
import java.util.stream.Collectors;

public class GestorCuota {
	
	public static Cuota crearCuota(CuotaDTO dto) {
		Cuota cuota = new Cuota();
		cuota.setFechaVencimiento(dto.getFechaVencimiento());
		cuota.setImporteTotal(dto.getImporteTotal());
		cuota.setEstado(EstadoCuota.PENDIENTE);
		cuota.setInteresAsociadoPorcentual(0F);
		return cuota;
	}
	
	public static List<Cuota> crearCuotas(List<CuotaDTO> dtos){
		List<Cuota> cuotas = dtos.stream().
				map(dto -> crearCuota(dto)).
				collect(Collectors.toList());
		return cuotas;
	}
}
