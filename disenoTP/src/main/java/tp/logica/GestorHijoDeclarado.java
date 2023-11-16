package tp.logica;

import tp.dto.HijoDeclaradoDTO;
import tp.entidad.HijoDeclarado;
import tp.entidad.PorcentajeCantHijos;
import tp.dao.HijoDeclaradoDAO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class GestorHijoDeclarado {
	
	public HijoDeclarado crearHijoDeclarado(HijoDeclaradoDTO dto) {
		if(!this.datosObligatoriosPresentes(dto)) {
			//TODO: throw DatosObligatoriosAusentesException
		}
		if(!this.fechaDeNacimientoEsValida(dto)) {
			// TODO: throw FechaNacimientoHijoInvalidaException
		}
		HijoDeclarado hijoDeclarado = new HijoDeclarado();
		hijoDeclarado.setSexo(dto.getSexo());
		hijoDeclarado.setEstadoCivil(dto.getEstadoCivil());
		hijoDeclarado.setFechaNacimiento(dto.getFechaNacimiento());
		return hijoDeclarado;
	}
	
	public List<HijoDeclarado> crearHijosDeclarados(List<HijoDeclaradoDTO> dtos){
		List<HijoDeclarado> hijosDeclarados = dtos.stream().
				map(dto -> crearHijoDeclarado(dto)).
				collect(Collectors.toList());
		return hijosDeclarados;
	}
	
	public boolean fechaDeNacimientoEsValida(HijoDeclaradoDTO dto) {
		LocalDate now = LocalDate.now();
		LocalDate fechaNacimiento = dto.getFechaNacimiento();
		Period edad = Period.between(fechaNacimiento,now);
		int añosDeEdad = edad.getYears();
		return añosDeEdad >= HijoDeclaradoDTO.MIN_EDAD && añosDeEdad <= HijoDeclaradoDTO.MAX_EDAD;
	}
	
	public boolean datosObligatoriosPresentes(HijoDeclaradoDTO dto) {
		boolean datosPresentes = dto != null;
		if(datosPresentes == false) {
			return false;
		}
		datosPresentes &= dto.getSexo() != null;
		datosPresentes &= dto.getEstadoCivil() != null;
		datosPresentes &= dto.getFechaNacimiento() != null;
		return datosPresentes;
	}
	
	public float getPorcentajeCantHijos() {
		HijoDeclaradoDAO dao = new HijoDeclaradoDAO();
		PorcentajeCantHijos porcentaje = dao.getPorcentajePorHijoActual();
		return porcentaje.getValorNumerico();
	}
}
