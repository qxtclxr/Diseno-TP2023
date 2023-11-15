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
	
	//TODO
	public RangoCantSiniestrosDTO getDTO(RangoCantSiniestros siniestros) {
		RangoCantSiniestrosDTO dto = new RangoCantSiniestrosDTO();
		dto.setId(siniestros.getId());
		dto.setNombre(siniestros.getNombre());
		return dto;
	}
	
	public List<RangoCantSiniestrosDTO> getAll(){
		RangoCantSiniestrosDAO dao = new RangoCantSiniestrosDAO();
		List<RangoCantSiniestros> all = dao.getAll();
		List<RangoCantSiniestrosDTO> allDTOs = all.stream().map(obj -> this.getDTO(obj)).collect(Collectors.toList());
		return allDTOs;
	}
}
