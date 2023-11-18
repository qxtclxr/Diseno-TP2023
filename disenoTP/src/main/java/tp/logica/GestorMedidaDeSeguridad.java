package tp.logica;

import tp.dto.*;
import tp.entidad.*;
import tp.exception.ObjetoNoEncontradoException;
import tp.dao.*;
import java.util.List;
import java.util.stream.Collectors;

public class GestorMedidaDeSeguridad {
	
	public static MedidaDeSeguridad getMedidaDeSeguridad(MedidaDeSeguridadDTO dto)
			throws ObjetoNoEncontradoException {
		MedidaDeSeguridadDAO dao = new MedidaDeSeguridadDAO();
		return dao.getById(dto.getId()).orElseThrow(() -> new ObjetoNoEncontradoException());
	}
	
	public static MedidaDeSeguridadDTO getDTO (MedidaDeSeguridad entidad) {
		MedidaDeSeguridadDTO dto = new MedidaDeSeguridadDTO();
		dto.setId(Long.valueOf(entidad.getIdMedidaDeSeguridad()));
		dto.setPregunta(entidad.getPregunta());
		return dto;
	}
	
	public static List<MedidaDeSeguridad> getAll(){
		MedidaDeSeguridadDAO dao = new MedidaDeSeguridadDAO();
		return dao.getAll();
	}
	
	public static List<MedidaDeSeguridadDTO> getAllDAOs(){
		List<MedidaDeSeguridad> medidas = getAll();
		List<MedidaDeSeguridadDTO> medidasDto = medidas.stream().
				map(entidad -> getDTO(entidad)).
				collect(Collectors.toList());
		return medidasDto;
	}

	public static PorcentajeMedidaDeSeguridad getPorcentajeMedidaDeSeguridadActual(MedidaDeSeguridadDTO dto)
			throws ObjetoNoEncontradoException {
		MedidaDeSeguridad medida = getMedidaDeSeguridad(dto);
		return medida.getValorActualPorcMedidaDeSeg();
	}
}
