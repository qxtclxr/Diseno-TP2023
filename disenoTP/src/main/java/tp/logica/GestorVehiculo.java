package tp.logica;

import java.time.LocalDateTime;

import tp.dto.ModeloDTO;
import tp.dto.VehiculoDTO;
import tp.entidad.Modelo;
import tp.dao.ModeloDAO;

public class GestorVehiculo {
	public boolean esMayorADiezAños(VehiculoDTO dto) {
		return (LocalDateTime.now().getYear() - dto.getAño()) > 10;
	}
	
	public boolean datosObligatoriosPresentes(VehiculoDTO dto) {
		boolean datosPresentes = dto != null;
		datosPresentes &= dto.getModelo() != null;
		if(datosPresentes==false) {
			return false;
		}
		datosPresentes &= dto.getModelo().getMarca() != null;
		datosPresentes &= dto.getAño() != null;
		datosPresentes &= dto.getMotor() != null;
		datosPresentes &= dto.getChasis() != null;
		return datosPresentes;
	}
	
	public float getValorModelo(ModeloDTO dto) {
		ModeloDAO dao = new ModeloDAO();
		Modelo modelo = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
		float valorModelo = dao.getValorModelo(modelo);
		return valorModelo;
	}
}
