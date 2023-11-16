package tp.logica;

import java.util.List;
import java.util.stream.Collectors;
import tp.dao.*;
import tp.entidad.*;
import tp.dto.*;

public class GestorRangoCantSiniestros {
	public RangoCantSiniestros getRangoCantSiniestros(RangoCantSiniestrosDTO dto) {
		RangoCantSiniestrosDAO dao = new RangoCantSiniestrosDAO();
		RangoCantSiniestros objeto = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
		return objeto;
	}
	
	public RangoCantSiniestros getRangoCantSiniestros(int cantSiniestros) {
		RangoCantSiniestrosDAO dao = new RangoCantSiniestrosDAO();
		RangoCantSiniestros objeto = new RangoCantSiniestros();
		//VER SI HAY ALGUNA MEJOR MANERA QUE USAR EL CONCEPTO PARA BUSCAR LOS OBJETOS
		if(cantSiniestros == 0) {
			objeto = dao.getByConcepto("Ninguno").orElseThrow(/*TODO*/);
		}else if(cantSiniestros == 1) {
			objeto = dao.getByConcepto("1").orElseThrow(/*TODO*/);
		}else if(cantSiniestros == 2) {
			objeto = dao.getByConcepto("2").orElseThrow(/*TODO*/);
		}else if(cantSiniestros > 2) {
			objeto = dao.getByConcepto("Más de 2").orElseThrow(/*TODO*/);
		}else {
			throw new NoExisteRangoCantSiniestos;
		}
	}
	
	public RangoCantSiniestrosDTO getDTO(RangoCantSiniestros siniestros) {
		RangoCantSiniestrosDTO dto = new RangoCantSiniestrosDTO();
		dto.setId(siniestros.getIdRangoCantSiniestros());
		dto.setNombre(siniestros.getConcepto());
		return dto;
	}
	
	public List<RangoCantSiniestros> getAll(){
		RangoCantSiniestrosDAO dao = new RangoCantSiniestrosDAO();
		List<RangoCantSiniestros> all = dao.getAll();
		return all;
	}
	
	public List<RangoCantSiniestrosDTO> getAllDTOs(){
		List<RangoCantSiniestros> all = this.getAll();
		List<RangoCantSiniestrosDTO> allDtos = all.stream().
				map(obj -> this.getDTO(obj)).
				collect(Collectors.toList());
		return allDtos;
	}
	
}
