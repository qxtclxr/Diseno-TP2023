package tp.logica;

import java.util.Optional;

import tp.dao.CoberturaDAO;
import tp.dao.PolizaDAO;
import tp.dto.CoberturaDTO;
import tp.entidad.*;

public class GestorPoliza {
	
	//TODO
	public boolean existePatente(String patente) {
//		PolizaDAO dao = new PolizaDAO();
//		Optional<Poliza> poliza = dao.getByPatente(patente);
//		return vehiculo.isPresent();
		return false;
	}
	
	//TODO
	public boolean existeMotor(String motor) {
//		PolizaDAO dao = new PolizaDAO();
//		Optional<Poliza> poliza = dao.getByMotor(motor);
//		return poliza.isPresent();
		return false;
	}
	
	//TODO
	public boolean existeChasis(String chasis) {
//		PolizaDAO dao = new PolizaDAO();
//		Optional<Poliza> poliza = dao.getByChasis(chasis);
//		return poliza.isPresent();
		return false;
	}
	
	public Cobertura getCobertura(CoberturaDTO dto) {
		CoberturaDAO dao = new CoberturaDAO();
		Cobertura cobertura = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
		return cobertura;
	}
	
	public 
}
