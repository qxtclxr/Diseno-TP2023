package tp.logica;

import java.util.List;
import java.util.stream.Collectors;
import tp.entidad.*;
import tp.exception.ObjetoNoEncontradoException;
import tp.dao.*;
import tp.dto.*;

public class GestorRangoKMRealizados {
	public static RangoKMRealizados getRangoKMRealizados(RangoKMRealizadosDTO dto)
			throws ObjetoNoEncontradoException {
		RangoKMRealizadosDAO dao = new RangoKMRealizadosDAO();
		RangoKMRealizados objeto = dao.getById(dto.getId()).orElseThrow(() -> new ObjetoNoEncontradoException());
		return objeto;
	}
	
	public static RangoKMRealizadosDTO getDTO(RangoKMRealizados kmRealizados) {
		RangoKMRealizadosDTO dto = new RangoKMRealizadosDTO();
		dto.setId(kmRealizados.getIdRangoKMRealizados());
		dto.setNombre(kmRealizados.getConcepto());
		return dto;
	}
	
	public static List<RangoKMRealizados> getAll(){
		RangoKMRealizadosDAO dao = new RangoKMRealizadosDAO();
		List<RangoKMRealizados> all = dao.getAll();
		return all;
	}
	
	public static List<RangoKMRealizadosDTO> getAllDTOs(){
		List<RangoKMRealizados> all = getAll();
		List<RangoKMRealizadosDTO> allDtos = all.stream().
				map(obj -> getDTO(obj)).
				collect(Collectors.toList());
		return allDtos;
	}

	public static PorcentajeKMRealizados getPorcentajeKMRealizadosActual(RangoKMRealizadosDTO dto)
			throws ObjetoNoEncontradoException {
		RangoKMRealizados rangoKm = getRangoKMRealizados(dto);
		return rangoKm.getValorActualPorcentajeKMRealizados();
	}
}
