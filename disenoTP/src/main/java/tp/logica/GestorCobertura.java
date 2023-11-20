package tp.logica;

import tp.dto.*;
import tp.entidad.*;
import tp.exception.ObjetoNoEncontradoException;

import java.util.List;
import java.util.stream.Collectors;

import tp.dao.*;

public class GestorCobertura {
	public static Cobertura getCobertura(CoberturaDTO dto) throws ObjetoNoEncontradoException {
		CoberturaDAO dao = new CoberturaDAO();
		Cobertura objeto = dao.getById(dto.getId()).orElseThrow(() -> new ObjetoNoEncontradoException());
		return objeto;
	}
	
	public static CoberturaDTO getDTO(Cobertura cobertura) {
		CoberturaDTO dto = new CoberturaDTO();
		dto.setNombre(cobertura.getTipoCobertura());
		dto.setId(cobertura.getIdCobertura());
		return dto;
	}
	
	public static List<Cobertura> getAll(){
		CoberturaDAO dao = new CoberturaDAO();
		List<Cobertura> all = dao.getAll();
		return all;
	}
	
	public static List<CoberturaDTO> getAllDTOs(){
		List<Cobertura> all = getAll();
		List<CoberturaDTO> allDTOs = all.stream().map(obj -> getDTO(obj)).collect(Collectors.toList());
		return allDTOs;
	}
	
	public static PorcentajeCobertura getPorcentajeCoberturaActual(CoberturaDTO dto) throws ObjetoNoEncontradoException {
		Cobertura cobertura = getCobertura(dto);
		return cobertura.getValorActualPorcentajeCobertura();
	}
}
