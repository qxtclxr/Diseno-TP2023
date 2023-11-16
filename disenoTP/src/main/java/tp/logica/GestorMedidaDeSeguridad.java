package tp.logica;

import tp.dto.*;
import tp.entidad.*;
import tp.dao.*;
import java.util.List;
import java.util.stream.Collectors;

public class GestorMedidaDeSeguridad {
	
	public RespuestaSeguridad crearRespuestaSeguridad(RespuestaSeguridadDTO dto) {
		RespuestaSeguridad respuesta = new RespuestaSeguridad();
		MedidaDeSeguridad medida = this.getMedidaDeSeguridad(dto.getMedida());
		respuesta.setMedida(medida);
		respuesta.setValorRespuesta(dto.getRespuesta());
		return respuesta;
	}
	
	public List<RespuestaSeguridad> crearRespuestasSeguridad(List<RespuestaSeguridadDTO> dtos){
		List<RespuestaSeguridad> respuestas = dtos.stream().
				map(dto -> crearRespuestaSeguridad(dto)).
				collect(Collectors.toList());
		return respuestas;
	}
	
	public MedidaDeSeguridad getMedidaDeSeguridad(MedidaDeSeguridadDTO dto) {
		MedidaDeSeguridadDAO dao = new MedidaDeSeguridadDAO();
		return dao.getById(dto.getId()).orElseThrow(/*TODO*/);
	}
	
	public MedidaDeSeguridadDTO getDTO (MedidaDeSeguridad entidad) {
		MedidaDeSeguridadDTO dto = new MedidaDeSeguridadDTO();
		dto.setId(Long.valueOf(entidad.getIdMedidaDeSeguridad()));
		dto.setPregunta(entidad.getPregunta());
		return dto;
	}
	
	public List<MedidaDeSeguridad> getAll(){
		MedidaDeSeguridadDAO dao = new MedidaDeSeguridadDAO();
		return dao.getAll();
	}
	
	public List<MedidaDeSeguridadDTO> getAllDAOs(){
		List<MedidaDeSeguridad> medidas = this.getAll();
		List<MedidaDeSeguridadDTO> medidasDto = medidas.stream().
				map(entidad -> this.getDTO(entidad)).
				collect(Collectors.toList());
		return medidasDto;
	}
}
