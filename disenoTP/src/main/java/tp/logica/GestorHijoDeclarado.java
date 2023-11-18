package tp.logica;

import tp.dto.HijoDeclaradoDTO;
import tp.entidad.AjusteHijos;
import tp.entidad.HijoDeclarado;
import tp.dao.AjusteHijosDAO;
import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.stream.Collectors;

public class GestorHijoDeclarado {
	
	public static HijoDeclarado crearHijoDeclarado(HijoDeclaradoDTO dto) {
		if(!datosObligatoriosPresentes(dto)) {
			//TODO: throw DatosObligatoriosAusentesException
		}
		if(!fechaDeNacimientoEsValida(dto)) {
			// TODO: throw FechaNacimientoHijoInvalidaException
		}
		HijoDeclarado hijoDeclarado = new HijoDeclarado();
		hijoDeclarado.setSexo(dto.getSexo());
		hijoDeclarado.setEstadoCivil(dto.getEstadoCivil());
		hijoDeclarado.setFechaNacimiento(dto.getFechaNacimiento());
		return hijoDeclarado;
	}
	
	public static List<HijoDeclarado> crearHijosDeclarados(List<HijoDeclaradoDTO> dtos){
		List<HijoDeclarado> hijosDeclarados = dtos.stream().
				map(dto -> crearHijoDeclarado(dto)).
				collect(Collectors.toList());
		return hijosDeclarados;
	}
	
	public static boolean fechaDeNacimientoEsValida(HijoDeclaradoDTO dto) {
		LocalDate now = LocalDate.now();
		LocalDate fechaNacimiento = dto.getFechaNacimiento();
		Period edad = Period.between(fechaNacimiento,now);
		int aniosDeEdad = edad.getYears();
		return aniosDeEdad >= HijoDeclaradoDTO.MIN_EDAD && aniosDeEdad <= HijoDeclaradoDTO.MAX_EDAD;
	}
	
	public static boolean datosObligatoriosPresentes(HijoDeclaradoDTO dto) {
		boolean datosPresentes = dto != null;
		if(datosPresentes == false) {
			return false;
		}
		datosPresentes &= dto.getSexo() != null;
		datosPresentes &= dto.getEstadoCivil() != null;
		datosPresentes &= dto.getFechaNacimiento() != null;
		return datosPresentes;
	}
	
	public static float getPorcentajeAjusteHijosActual() {
		AjusteHijosDAO dao = new AjusteHijosDAO();
		AjusteHijos ajuste = dao.getAll().get(0);
		return ajuste.getValorActualPorcentajeCantHijos().getValorNumerico();
	}
}
