package tp.logica;

import tp.entidad.*;
import tp.dto.*;

import java.time.LocalDate;
import java.util.ArrayList;
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
	
	public static List<CuotaDTO> generarCuotas(PolizaDTO dto){
		List<CuotaDTO> cuotaDtos = new ArrayList<CuotaDTO>();
		switch(dto.getTipoPoliza()) {
			case SEMESTRAL: {
				CuotaDTO cuota = new CuotaDTO();
				cuota.setEstado(EstadoCuota.PENDIENTE);
				cuota.setFechaVencimiento(dto.getFechaInicio().minusDays(1));
				cuota.setImporteTotal(dto.getImporteTotal());
				cuota.setOrden(1);
				cuotaDtos.add(cuota);
				break;
			}
			case MENSUAL: {
				float importeMensual = dto.getImporteTotal() / 6;
				LocalDate fechaPrimerPago = dto.getFechaInicio().minusDays(1);
				for(int i = 0 ; i < 6 ; i++) {
					CuotaDTO cuota = new CuotaDTO();
					cuota.setEstado(EstadoCuota.PENDIENTE);
					cuota.setFechaVencimiento(fechaPrimerPago.plusMonths(i));
					cuota.setImporteTotal(importeMensual);
					cuota.setOrden(i+1);
					cuotaDtos.add(cuota);
				}
				break;
			}
		}
		return cuotaDtos;
	}
}
