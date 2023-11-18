package tp.logica;

import java.util.List;
import java.util.stream.Collectors;
import tp.dao.*;
import tp.entidad.*;
import tp.dto.*;

public class GestorRangoCantSiniestros {
	public static RangoCantSiniestros getRangoCantSiniestros(RangoCantSiniestrosDTO dto) {
		RangoCantSiniestrosDAO dao = new RangoCantSiniestrosDAO();
		RangoCantSiniestros objeto = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
		return objeto;
	}
	
	public static RangoCantSiniestros getRangoCantSiniestros(int cantSiniestros) {
		RangoCantSiniestrosDAO dao = new RangoCantSiniestrosDAO();
		RangoCantSiniestros objeto = new RangoCantSiniestros();
		if(cantSiniestros == 0) {
			objeto = dao.getByMinimo(0).orElseThrow(/*TODO*/);
		}else if(cantSiniestros == 1) {
			objeto = dao.getByMinimo(1).orElseThrow(/*TODO*/);
		}else if(cantSiniestros == 2) {
			objeto = dao.getByMinimo(2).orElseThrow(/*TODO*/);
		}else if(cantSiniestros > 2) {
			objeto = dao.getByMinimo(3).orElseThrow(/*TODO*/);
		}else {
			//TODO: throw new NoExisteRangoCantSiniestos;
		}
		return objeto;
	}
	
	public static RangoCantSiniestrosDTO getDTO(RangoCantSiniestros siniestros) {
		RangoCantSiniestrosDTO dto = new RangoCantSiniestrosDTO();
		dto.setId(siniestros.getIdRangoCantSiniestros());
		dto.setNombre(siniestros.getConcepto());
		return dto;
	}
	
	public static List<RangoCantSiniestros> getAll(){
		RangoCantSiniestrosDAO dao = new RangoCantSiniestrosDAO();
		List<RangoCantSiniestros> all = dao.getAll();
		return all;
	}
	
	public static List<RangoCantSiniestrosDTO> getAllDTOs(){
		List<RangoCantSiniestros> all = getAll();
		List<RangoCantSiniestrosDTO> allDtos = all.stream().
				map(obj -> getDTO(obj)).
				collect(Collectors.toList());
		return allDtos;
	}

	public static PorcentajeCantSiniestros getPorcentajeCantSiniestrosActual(
			RangoCantSiniestrosDTO dto) {
		RangoCantSiniestros rango = getRangoCantSiniestros(dto);
		return rango.getValorActualPorcentajeCantSiniestros();
	}
	
}
