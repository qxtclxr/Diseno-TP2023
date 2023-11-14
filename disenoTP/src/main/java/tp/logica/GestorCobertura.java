package tp.logica;

import tp.dto.*;
import tp.entidad.*;
import java.util.List;
import java.util.stream.Collectors;

import tp.dao.*;

public class GestorCobertura {
	public Cobertura getCobertura(CoberturaDTO dto) {
		CoberturaDAO dao = new CoberturaDAO();
		Cobertura objeto = dao.getById(dto.getId()).orElseThrow(/*TODO*/);
		return objeto;
	}
	
	//TODO
	public CoberturaDTO getDTO(Cobertura cobertura) {
		CoberturaDTO dto = new CoberturaDTO();
		return dto;
	}
	
	public List<CoberturaDTO> getAll(){
		CoberturaDAO dao = new CoberturaDAO();
		List<Cobertura> all = dao.getAll();
		List<CoberturaDTO> allDTOs = all.stream().map(obj -> this.getDTO(obj)).collect(Collectors.toList());
		return allDTOs;
	}
}
